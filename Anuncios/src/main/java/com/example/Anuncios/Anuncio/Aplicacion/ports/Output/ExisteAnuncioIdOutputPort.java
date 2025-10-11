package com.example.Anuncios.Anuncio.Aplicacion.ports.Output;

import java.util.UUID;

public interface ExisteAnuncioIdOutputPort {
    Boolean existeAnuncio(UUID idAnuncio);
}
