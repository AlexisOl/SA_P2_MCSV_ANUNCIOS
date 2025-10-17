package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.CrearAnuncioInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.CrearAnuncioOutputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.Eventos.VerificarCineOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.EnvioMensajesCorreoDTO;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne.VerificarCineDTO;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne.VerificarCineRespuestaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@Service
public class CrearAnuncioService implements CrearAnuncioInputPort {

    private CrearAnuncioOutputPort crearAnuncioOutputPort;
    private final VerificarCineOutputPort verificarCineOutputPort;
    // para la comunicacino con kafka

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final ConcurrentHashMap<String, CompletableFuture<Boolean>> cineVerificationFutures= new ConcurrentHashMap<>();

    public CrearAnuncioService(CrearAnuncioOutputPort crearAnuncioOutputPort,
                               KafkaTemplate<String, String> kafkaTemplate,
                               ObjectMapper objectMapper,
                               VerificarCineOutputPort verificarCineOutputPort
                             ){
        this.crearAnuncioOutputPort = crearAnuncioOutputPort;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.verificarCineOutputPort = verificarCineOutputPort;
    }
    @Override
    public Anuncio crearAnuncio(CrearAnuncioDTO anuncio)  {
        System.out.println(anuncio.getCostoVisibilidad()+ " - "+ anuncio.getTitulo() +"--");
        // verificar cine 

        boolean cineExists = false;
//        try {
//            cineExists = this.verificarCineOutputPort.verificarCine(anuncio.getIdCine()).get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (!cineExists) {
//            throw new IllegalArgumentException("El cine con ID " + anuncio.getIdCine() + " no existe.");
//        }

        Anuncio objetoAnuncio =new Anuncio(
                UUID.randomUUID(),
                anuncio.getTitulo(),
                TipoAnuncio.valueOf(anuncio.getTipo()),
                new CostosAnuncio(
                        anuncio.getCostoVisibilidad(),
                        anuncio.getCostoOcultacion()
                )
                // anuncio.getActivo()
        );

       Anuncio nuevoAnuncio= this.crearAnuncioOutputPort.crearAnuncio(objetoAnuncio);




        try {
            // Crear DTO para notificación
            EnvioMensajesCorreoDTO correoDTO = new EnvioMensajesCorreoDTO();
            correoDTO.setCorreo("carlosovalle202031064@cunoc.edu.gt");
            correoDTO.setMensaje("Nuevo anuncio creado");
            correoDTO.setDescripcion("Se creó el anuncio con título: " + anuncio.getTitulo());

            // Convertir a JSON
            String mensaje = objectMapper.writeValueAsString(correoDTO);

            // Publicar en Kafka
            kafkaTemplate.send("creacion-auncio", mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return nuevoAnuncio;
    }




}
