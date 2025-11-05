package com.example.Anuncios.PropiedadAnuncio.Aplicacion.Factory;


import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.EstadoAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class MaterialAnuncioFactory {
    private static final String S3_BASE_URL = "https://sa-p2-img.s3.us-east-1.amazonaws.com/anuncios/";

    public MaterialAnuncio enriquecerConUrls(MaterialAnuncio material) {
        if (material == null) return null;

        return new MaterialAnuncio(
                material.getId(),
                material.getTexto(),
                enriquecer(material.getLinkvideo()),
                enriquecer(material.getLinkimagen()),
                material.getIdAnuncio()
        );
    }

    private String enriquecer(String filename) {
        return (filename != null && !filename.isBlank()) ? S3_BASE_URL + filename : null;
    }
}