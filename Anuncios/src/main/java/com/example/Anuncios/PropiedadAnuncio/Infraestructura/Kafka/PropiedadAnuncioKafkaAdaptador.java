package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.Eventos.VerificarCineOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CompletarEstadoPropiedadAnuncio.CompletarEstadoPropiedadAnuncioService;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.VerificarSaldoCineOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioCreadoDTO;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioFallidoDTO;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.CineActualizadoDTO;
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

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class PropiedadAnuncioKafkaAdaptador implements VerificarSaldoCineOutputPort {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final CompletarEstadoPropiedadAnuncioService completarEstadoPropiedadAnuncioService;


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
        this.completarEstadoPropiedadAnuncioService.completarPropiedadAnuncio(
                evento.getAnuncioId(),
                false,
                evento.getMotivoFallo()
        );
    }

}
