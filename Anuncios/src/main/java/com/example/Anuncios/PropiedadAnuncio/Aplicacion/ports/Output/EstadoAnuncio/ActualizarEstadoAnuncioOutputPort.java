package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio;

import java.util.UUID;

public interface ActualizarEstadoAnuncioOutputPort {
    void actualizarEstadoAnuncio(UUID anuncioId, String estado);
}
