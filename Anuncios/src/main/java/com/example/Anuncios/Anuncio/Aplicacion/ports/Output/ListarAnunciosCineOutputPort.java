package com.example.Anuncios.Anuncio.Aplicacion.ports.Output;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;

import java.util.List;
import java.util.UUID;

public interface ListarAnunciosCineOutputPort {
    List<Anuncio> listarAnunciosCine(UUID idCine);

}
