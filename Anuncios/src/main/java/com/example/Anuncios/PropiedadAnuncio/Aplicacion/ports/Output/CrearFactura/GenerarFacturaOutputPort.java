package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearFactura;

import com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO;

public interface GenerarFacturaOutputPort {
    void generarFactura(AnuncioCreadoDTO anuncioCreado);

}
