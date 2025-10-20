package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;

import java.util.UUID;

public interface ListarMaterialAnuncioEspecificoInputPort {

    MaterialAnuncio  getMaterialAnuncio(UUID idMaterialAnuncio);
}
