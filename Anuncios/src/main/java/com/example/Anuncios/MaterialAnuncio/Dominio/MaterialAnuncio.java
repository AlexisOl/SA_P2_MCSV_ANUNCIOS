package com.example.Anuncios.MaterialAnuncio.Dominio;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MaterialAnuncio {
    private  UUID id;
    private  String linkvideo;
    private  String texto;
    private  String linkimagen;
    private Anuncio idAnuncio;


    public MaterialAnuncio(UUID id, String texto, String linkvideo, String linkimagen, Anuncio idAnuncio) {
        this.id = id;
        this.texto = texto;
        this.linkvideo = linkvideo;
        this.linkimagen = linkimagen ;
        this.idAnuncio = idAnuncio;
    }

    public MaterialAnuncio() {}




}
