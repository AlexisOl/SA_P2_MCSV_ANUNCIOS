package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

import java.time.LocalDate;
import java.util.List;

public interface ListaAnunciosActualesMaterialInputPort {
    List<PropiedadAnuncio> listaAnunciosActualesMaterial(LocalDate fechainicio, LocalDate fechafin);

}
