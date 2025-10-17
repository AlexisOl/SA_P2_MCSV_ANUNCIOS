package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.EstadoAnuncio;

import java.util.UUID;

public interface CompletarPropiedadAnuncioInputPort {
    public void completarPropiedadAnuncio(UUID propiedadAnuncioId, boolean exito, String motivoFallo);
}
