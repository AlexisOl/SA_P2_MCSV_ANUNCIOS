package com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model;

import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import lombok.Value;

import java.util.UUID;

@Value
public class ResponseAnuncioDTO {
    private UUID id;
    private String titulo;
    private TipoAnuncio tipo;
    private CostosAnuncio costo;
    private Boolean activo;
    private UUID idCine ;

}
