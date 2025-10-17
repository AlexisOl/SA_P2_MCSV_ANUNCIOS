package com.example.Anuncios.PropiedadAnuncio.Dominio;


import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PropiedadAnuncio {
    private UUID id;
    private LocalDate fecha;
    private LocalDate fechaFin;
    private UUID usuario;
    private Anuncio anuncio;
    private VigenciaAnuncio vigencia;
    private String estado;


    public PropiedadAnuncio(UUID id, LocalDate fecha, LocalDate fechaFin, UUID usuario,Anuncio anuncio, VigenciaAnuncio vigencia,
                            String estado) {
        this.id = id;
        this.fecha = fecha;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.anuncio = anuncio;
        this.vigencia = vigencia;
        this.estado= estado;

    }
}
