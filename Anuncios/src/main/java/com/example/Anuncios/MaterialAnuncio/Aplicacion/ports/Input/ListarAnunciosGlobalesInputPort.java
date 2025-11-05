package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;

import java.util.List;

public interface ListarAnunciosGlobalesInputPort {
    List<MaterialAnuncio> getListaMaterialAnuncios();

}
