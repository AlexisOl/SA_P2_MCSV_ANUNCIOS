package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.PropiedadAnuncio.Dominio.EstadoAnuncio;

import java.util.UUID;

public interface CambioEstadoAnuncioOutputPort {
    void cambioEstadoAnuncioOutputPort(UUID id, EstadoAnuncio estadoVenta);
}
