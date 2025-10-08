package com.example.Anuncios.Anuncio.Aplicacion.ports.Output;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;

public interface CrearAnuncioOutputPort {
    Anuncio crearAnuncio(Anuncio anuncio);

}
