package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Mapper;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Model.ResponsePropiedadAnuncioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PropiedadAnuncioRestMapper {
    ResponsePropiedadAnuncioDTO toResponsePropiedadAnuncioDto(PropiedadAnuncio propiedadAnuncio);
}
