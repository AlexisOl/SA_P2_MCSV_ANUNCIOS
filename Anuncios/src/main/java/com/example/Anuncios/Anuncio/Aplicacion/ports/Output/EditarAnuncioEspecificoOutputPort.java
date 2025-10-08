package com.example.Anuncios.Anuncio.Aplicacion.ports.Output;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.EditarAnuncioEspecifico.EditarAnuncioDTO;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;

import java.util.UUID;

public interface EditarAnuncioEspecificoOutputPort {
    Anuncio EditarAnuncioEspecifico(UUID id, EditarAnuncioDTO anuncio);
}
