package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;


public class CrearPropiedadAnuncioDTO {
    private UUID usuario;
    private UUID anuncio;
    private String vigencia;

    public CrearPropiedadAnuncioDTO() {}
    public CrearPropiedadAnuncioDTO(UUID usuario, UUID anuncio, String vigencia) {
        this.usuario = usuario;
        this.anuncio = anuncio;
        this.vigencia = vigencia;
    }

    public UUID getUsuario() { return usuario; }
    public UUID getAnuncio() { return anuncio; }
    public String getVigencia() { return vigencia; }
}
