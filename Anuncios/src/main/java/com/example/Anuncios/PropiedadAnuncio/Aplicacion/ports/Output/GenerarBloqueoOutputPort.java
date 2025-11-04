package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;

public interface GenerarBloqueoOutputPort {
    void generarBloqueo(BloqueoCineDTO peticion);

}
