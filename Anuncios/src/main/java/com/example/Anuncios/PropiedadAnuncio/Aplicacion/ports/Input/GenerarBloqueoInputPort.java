package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input;

import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;

public interface GenerarBloqueoInputPort {
    void generarBloqueo(BloqueoCineDTO peticion);
}
