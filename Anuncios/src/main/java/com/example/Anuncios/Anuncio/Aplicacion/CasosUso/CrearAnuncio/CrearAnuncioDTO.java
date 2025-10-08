package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio;

import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrearAnuncioDTO {
    private String titulo;
    private String tipo;
    private Double costoVisibilidad;
    private Double costoOcultacion;

    private Boolean activo;
    private UUID idCine ;

}
