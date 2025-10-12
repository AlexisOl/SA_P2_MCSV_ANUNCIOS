package com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioDTO;
import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;

public interface CrearBloqueoAnunciosCineInputPort {
    BloqueoAnuncio CrearBloqueoAnuncio(CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO);
}
