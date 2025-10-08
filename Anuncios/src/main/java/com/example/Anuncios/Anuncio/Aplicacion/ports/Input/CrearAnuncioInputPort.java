package com.example.Anuncios.Anuncio.Aplicacion.ports.Input;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;

public interface CrearAnuncioInputPort {
    Anuncio crearAnuncio(CrearAnuncioDTO anuncio);
}
