package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioCreadoDTO;

public interface VerificarSaldoCineOutputPort {
    void publicarAnuncioCreado(AnuncioCreadoDTO evento);
}
