package com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest.Mapper;

import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest.Model.ResponseMaterialAnuncioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface MaterialAnuncioRestMapper {
    ResponseMaterialAnuncioDTO toReponseMaterialAnuncio(MaterialAnuncio materialAnuncio);
}
