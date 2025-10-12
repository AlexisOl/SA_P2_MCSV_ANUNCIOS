package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio.CrearPropiedadAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

public interface CrearPropiedadAnuncioInputPort {

    PropiedadAnuncio crearPropiedadAnuncio(CrearPropiedadAnuncioDTO crearPropiedadAnuncioDTO);
}
