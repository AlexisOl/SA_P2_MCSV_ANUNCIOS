package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;

import java.util.List;

public interface ListarAnunciosGlobalesOutputPort {
    List<MaterialAnuncio> getListaMaterialAnuncios();
}
