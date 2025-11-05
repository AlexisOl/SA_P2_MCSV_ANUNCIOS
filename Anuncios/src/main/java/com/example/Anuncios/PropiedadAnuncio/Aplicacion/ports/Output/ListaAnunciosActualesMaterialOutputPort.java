package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

import java.time.LocalDate;
import java.util.List;

public interface ListaAnunciosActualesMaterialOutputPort {

    List<PropiedadAnuncio> listaAnunciosActualesMaterial(LocalDate fechainicio, LocalDate fechafin);

}
