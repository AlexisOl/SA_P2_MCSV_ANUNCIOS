package com.example.Anuncios.Anuncio.Aplicacion.ports.Output;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;

import java.util.UUID;

public interface ListarAnuncioEspecificoOutputPort {
    Anuncio listarAnuncioEspecifico(UUID id);
}
