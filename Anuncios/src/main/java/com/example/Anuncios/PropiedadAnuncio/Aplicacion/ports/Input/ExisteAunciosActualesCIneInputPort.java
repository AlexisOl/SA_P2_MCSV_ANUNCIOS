package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

import java.time.LocalDate;
import java.util.List;

public interface ExisteAunciosActualesCIneInputPort {

    List<PropiedadAnuncio> listaAnunciosActuales(LocalDate fechainicio, LocalDate fechafin);
}
