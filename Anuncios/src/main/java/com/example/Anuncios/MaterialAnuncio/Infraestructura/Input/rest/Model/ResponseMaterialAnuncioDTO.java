package com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest.Model;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import lombok.Value;

import java.util.UUID;

@Value
public class ResponseMaterialAnuncioDTO {
    private UUID id;
    private  String linkvideo;
    private  String texto;
    private  String linkimagen;
    private Anuncio idAnuncio;
}
