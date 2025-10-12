package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio.CrearPropiedadAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;

public interface CrearPropiedadAnuncioOutputPort {
    PropiedadAnuncio crearPropiedadAnuncio(PropiedadAnuncio anuncio);

}
