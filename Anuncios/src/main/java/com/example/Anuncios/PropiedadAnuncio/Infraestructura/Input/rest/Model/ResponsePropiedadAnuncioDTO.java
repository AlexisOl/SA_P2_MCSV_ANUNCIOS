package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Model;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;
@Value
public class ResponsePropiedadAnuncioDTO {
    private UUID id;
    private LocalDate fecha;
    private UUID usuaio;
    private Anuncio anuncio;
    private VigenciaAnuncio vigencia;

}
