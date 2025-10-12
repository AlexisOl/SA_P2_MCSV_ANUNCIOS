package com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;

import java.util.List;
import java.util.UUID;

public interface ListadoBloqueosCineInputPort {
    List<BloqueoAnuncio> listadoBloqueosCine(UUID  idCine);
}
