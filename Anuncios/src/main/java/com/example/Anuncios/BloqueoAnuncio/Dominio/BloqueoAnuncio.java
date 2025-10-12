package com.example.Anuncios.BloqueoAnuncio.Dominio;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BloqueoAnuncio {
    private UUID id;
    private LocalDate fecha;
    private LocalDate fecha_fin;
    private Long cantidad_dias;
    private UUID idCine;


    public BloqueoAnuncio(UUID id, LocalDate fecha, LocalDate fecha_fin,Long cantidad_dias, UUID idCine) {
        this.id = id;
        this.fecha = fecha;
        this.fecha_fin = fecha_fin;
        this.cantidad_dias = cantidad_dias;
        this.idCine = idCine;
    }
    public BloqueoAnuncio() {}

}
