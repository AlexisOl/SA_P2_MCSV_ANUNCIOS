package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

import java.util.List;

public interface ExisteAunciosActualesCIneInputPort {

    List<PropiedadAnuncio> listaAnunciosActuales();
}
