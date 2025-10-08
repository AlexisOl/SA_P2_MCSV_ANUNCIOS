package com.example.Anuncios.Anuncio.Dominio;

import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Anuncio {
    private UUID id;
    private String titulo;
    private TipoAnuncio tipo;
    private CostosAnuncio costo;
    private Boolean activo;
    private UUID idCine ;



    public Anuncio(UUID id, String titulo,  TipoAnuncio tipo, CostosAnuncio costo, UUID idCIne) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.costo = costo;
        this.activo = true;
        this.idCine = idCIne;
    }

}
