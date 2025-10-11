package com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
