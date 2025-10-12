package com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;

import java.util.List;
import java.util.UUID;

public interface ListadoBloqueosCineOutputPort {
    List<BloqueoAnuncio> listadoBloqueosCine(UUID idCine);

}
