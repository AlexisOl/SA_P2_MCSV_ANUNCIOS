package com.example.Anuncios.Anuncio.Aplicacion.ports.Input;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;

import java.util.List;
import java.util.UUID;

public interface ListarAnunciosCineInputPort {
    List<Anuncio> listarAnunciosCine(UUID idCine);
}
