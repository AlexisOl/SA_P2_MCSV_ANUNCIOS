package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class CineActualizadoDTO {
    private UUID anuncioId;
    private String correlationId;
}
