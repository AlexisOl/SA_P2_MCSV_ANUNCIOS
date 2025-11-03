package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;


import com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO;

public interface VerificarSaldoCineOutputPort {
    void publicarAnuncioCreado(AnuncioCreadoDTO evento);
}
