package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.CrearAnuncioInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.CrearAnuncioOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Eventos.EnvioMensajesCorreoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearAnuncioService implements CrearAnuncioInputPort {

    private CrearAnuncioOutputPort crearAnuncioOutputPort;

    // para la comunicacino con kafka

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CrearAnuncioService(CrearAnuncioOutputPort crearAnuncioOutputPort,
                               KafkaTemplate<String, String> kafkaTemplate,
                               ObjectMapper objectMapper){
        this.crearAnuncioOutputPort = crearAnuncioOutputPort;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    @Override
    public Anuncio crearAnuncio(CrearAnuncioDTO anuncio) {
        System.out.println(anuncio.getCostoVisibilidad()+ " - "+ anuncio.getTitulo() +"--");

        Anuncio objetoAnuncio =new Anuncio(
                UUID.randomUUID(),
                anuncio.getTitulo(),
                TipoAnuncio.valueOf(anuncio.getTipo()),
                new CostosAnuncio(
                        anuncio.getCostoVisibilidad(),
                        anuncio.getCostoOcultacion()
                ),
                anuncio.getIdCine()
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
