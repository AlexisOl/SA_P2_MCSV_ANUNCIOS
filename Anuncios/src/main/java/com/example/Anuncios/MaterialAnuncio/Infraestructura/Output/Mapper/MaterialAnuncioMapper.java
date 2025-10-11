package com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Mapper;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Entity.MaterialAnuncioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface MaterialAnuncioMapper {

    MaterialAnuncio toMaterialAnuncio(MaterialAnuncioEntity materialAnuncioEntity);

    MaterialAnuncioEntity  toMaterialAnuncioEntity(MaterialAnuncio materialAnuncioDTO);
}
