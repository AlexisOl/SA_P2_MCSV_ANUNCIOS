package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output;

import com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio.CrearMaterialAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;

public interface CrearMaterialAnuncioOuputPort {
    MaterialAnuncio crearMaterialAnuncio(MaterialAnuncio crearMaterialAnuncioDTO);

}
