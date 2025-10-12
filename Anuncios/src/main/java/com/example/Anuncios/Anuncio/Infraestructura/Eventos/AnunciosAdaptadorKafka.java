package com.example.Anuncios.Anuncio.Infraestructura.Eventos;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.Eventos.VerificarCineOutputPort;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne.VerificarCineDTO;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne.VerificarCineRespuestaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AnunciosAdaptadorKafka implements VerificarCineOutputPort {


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, CompletableFuture<Boolean>> cineVerificationFutures= new ConcurrentHashMap<>();

    public AnunciosAdaptadorKafka(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = "respuesta-verificar-cine", groupId = "anuncios-group")
    public void handleCineVerificationResponse(@Payload String mensaje, @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) {
            System.err.println("Missing correlationId in Kafka message from respuesta-verificar-cine");
            return;
        }
        VerificarCineRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarCineRespuestaDTO.class);
        CompletableFuture<Boolean> future = cineVerificationFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        } else {
            System.err.println("No future found for correlationId: " + correlationId);
        }
    }

    @Override
    public CompletableFuture<Boolean> verificarCine(UUID idCine) {
        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cineVerificationFutures.put(correlationId, future);

        // Create and send Kafka message
        try {
            String mensaje = objectMapper.writeValueAsString(new VerificarCineDTO(idCine, correlationId));
            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(mensaje)
                    .setHeader(KafkaHeaders.TOPIC, "verificar-cine")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();
            kafkaTemplate.send(kafkaMessage);
        } catch (Exception e) {
            cineVerificationFutures.remove(correlationId);
            future.completeExceptionally(new RuntimeException("Failed to send verification request: " + e.getMessage(), e));
        }

        return future;
    }
}
