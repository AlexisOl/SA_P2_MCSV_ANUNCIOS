package com.example.Anuncios.Anuncio.Dominio.ObjetosValor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostosAnuncio {
    private Double costoVisibilidad;
    private Double costoOcultacion;


    public CostosAnuncio(Double costoVisibilidad, Double costoOcultacion) {

        if(costoVisibilidad<= 0 || costoOcultacion<= 0){
            throw new IllegalArgumentException("No se puede registar precios negativos");
        }
        this.costoVisibilidad = costoVisibilidad;
        this.costoOcultacion = costoOcultacion;
    }
}
