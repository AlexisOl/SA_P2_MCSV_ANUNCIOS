package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

import java.time.LocalDate;
import java.util.List;

public interface ExisteAunciosActualesCIneOutputPort {
    List<PropiedadAnuncio> listaAnunciosActuales(LocalDate fechainicio, LocalDate fechafin);

}
