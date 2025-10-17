package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AnuncioCreadoDTO
{
    private UUID anuncioId;
    private UUID idCine;
    private double costo;
    private String correlationId;
}
