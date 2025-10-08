package com.example.Anuncios.Anuncio.Aplicacion.ports.Input;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;

import java.util.UUID;

public interface ListarAnuncioEspecificoInputPort {
    Anuncio listarAnuncioEspecifico(UUID id);

}
