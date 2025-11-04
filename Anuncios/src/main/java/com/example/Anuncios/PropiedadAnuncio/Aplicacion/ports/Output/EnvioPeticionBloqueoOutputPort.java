package com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output;

import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;
import com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO;

public interface EnvioPeticionBloqueoOutputPort {

    void generarBloqueo(BloqueoCineDTO bloqueoCineDTO);

}
