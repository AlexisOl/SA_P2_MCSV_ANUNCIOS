package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AnuncioFallidoDTO {
    private UUID anuncioId;
    private String motivoFallo;
    private String correlationId;
}
