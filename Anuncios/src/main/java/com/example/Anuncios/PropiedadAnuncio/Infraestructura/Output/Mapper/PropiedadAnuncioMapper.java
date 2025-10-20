package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Mapper;

import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Entity.PropiedadAnuncioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface PropiedadAnuncioMapper {
    PropiedadAnuncio toPropiedadAnuncio(PropiedadAnuncioEntity entity);

    PropiedadAnuncioEntity toPropiedadAnuncioEntity(PropiedadAnuncio entity);

}
