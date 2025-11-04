package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.Eventos.VerificarCineOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CompletarEstadoPropiedadAnuncio.CompletarEstadoPropiedadAnuncioService;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CambioEstadoAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearFactura.GenerarFacturaOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EnvioPeticionBloqueoOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ExisteAunciosActualesCIneOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.VerificarSaldoCineOutputPort;

import com.example.Anuncios.PropiedadAnuncio.Dominio.EstadoAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioFallidoDTO;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.CineActualizadoDTO;
import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;
import com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO;
import com.example.comun.DTO.FacturaAnuncio.CambioEstadoAnuncioDTO;
import com.example.comun.DTO.FacturaAnuncio.RespuestaFacturaAnuncioCreadaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class PropiedadAnuncioKafkaAdaptador implements VerificarSaldoCineOutputPort, GenerarFacturaOutputPort, EnvioPeticionBloqueoOutputPort {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final CompletarEstadoPropiedadAnuncioService completarEstadoPropiedadAnuncioService;
    private final CambioEstadoAnuncioOutputPort cambioEstadoAnuncioOutputPort;


    @Override
    public void publicarAnuncioCreado(AnuncioCreadoDTO evento) {
        try {
            String mensaje =  objectMapper.writeValueAsString(evento);
            Message<String> mensajeKafka = MessageBuilder
                    .withPayload(mensaje)
                    .setHeader(KafkaHeaders.TOPIC, "propiedad-anuncio-creado")
                    .setHeader(KafkaHeaders.CORRELATION_ID, evento.getCorrelationId())
                    .build();

            kafkaTemplate.send(mensajeKafka);
        } catch (Exception e) {
            throw new RuntimeException("Fallo en publicar el evento de propiedad anuncio: " + e.getMessage(), e);
        }
    }

    @Override
    public void generarFactura(com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO anuncioCreado) {
        try {
            String mensaje =  objectMapper.writeValueAsString(anuncioCreado);
            Message<String> mensajeKafka = MessageBuilder
                    .withPayload(mensaje)
                    .setHeader(KafkaHeaders.TOPIC, "generar-factura-anuncio")
                    .setHeader(KafkaHeaders.CORRELATION_ID, anuncioCreado.getCorrelationId())
                    .build();

            kafkaTemplate.send(mensajeKafka);
        } catch (Exception e) {
            throw new RuntimeException("Fallo en publicar el evento de propiedad anuncio: " + e.getMessage(), e);
        }
    }


    @Override
    public void generarBloqueo(BloqueoCineDTO bloqueoCineDTO) {
        try {
            String mensaje =  objectMapper.writeValueAsString(bloqueoCineDTO);
            Message<String> mensajeKafka = MessageBuilder
                    .withPayload(mensaje)
                    .setHeader(KafkaHeaders.TOPIC, "generar-bloqueo-anuncio")
                    .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                    .build();

            kafkaTemplate.send(mensajeKafka);
        } catch (Exception e) {
            throw new RuntimeException("Fallo en publicar el evento de propiedad anuncio: " + e.getMessage(), e);
        }
    }


    //-------------------------------------
    //-------------------------------------
    //-------------------------------------
    // escuachas
    @KafkaListener(topics = "cine-actualizado", groupId = "anuncios-group")
    public void handleCineActualizado(@Payload String mensaje,
                                      @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        CineActualizadoDTO evento = objectMapper.readValue(mensaje, CineActualizadoDTO.class);
        this.completarEstadoPropiedadAnuncioService.completarPropiedadAnuncio(
                evento.getAnuncioId(),
                true,
                null
        );
    }


    @KafkaListener(topics = "propiedad-anuncio-fallido", groupId = "anuncios-group")
    public void handleAnuncioFallido(@Payload String mensaje,
                                      @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        AnuncioFallidoDTO evento = objectMapper.readValue(mensaje, AnuncioFallidoDTO.class);

        System.out.println(evento.getAnuncioId()+ "aca deberia de decir cual");
        this.completarEstadoPropiedadAnuncioService.completarPropiedadAnuncio(
                evento.getAnuncioId(),
                false,
                evento.getMotivoFallo()
        );
    }

    @KafkaListener(topics = "cambio-estado-exitoso-anuncio", groupId = "anuncios-group")
    @Transactional
    public void manejarExitoFactura(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId
    )   throws Exception {

        CambioEstadoAnuncioDTO solicitud = objectMapper.readValue(mensaje, CambioEstadoAnuncioDTO.class);

        //cambio a la factura del usuario

        this.cambioEstadoAnuncioOutputPort.cambioEstadoAnuncioOutputPort(solicitud.getAnuncioId(),
                EstadoAnuncio.EXITOSA);



    }

    @KafkaListener(topics = "cambio-estado-fallido-anuncio", groupId = "anuncios-group")
    @Transactional
    public void manejarFalloFactura(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId
    )  throws Exception {
        //generar ingreso de facturacion
        CambioEstadoAnuncioDTO solicitud = objectMapper.readValue(mensaje, CambioEstadoAnuncioDTO.class);

        this.cambioEstadoAnuncioOutputPort.cambioEstadoAnuncioOutputPort(solicitud.getAnuncioId(),
                EstadoAnuncio.CANCELADA);


    }


}
