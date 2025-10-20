package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;

import java.util.UUID;

public interface ListarMaterialAnuncioEspecificoOutputPort {
    MaterialAnuncio getMaterialAnuncio(UUID idMaterialAnuncio);

}
