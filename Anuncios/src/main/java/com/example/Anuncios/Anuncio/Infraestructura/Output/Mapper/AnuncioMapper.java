package com.example.Anuncios.Anuncio.Infraestructura.Output.Mapper;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Output.Entity.AnuncioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnuncioMapper {

    @Mapping(target = "costoVisibilidad", source = "costo.costoVisibilidad")
    @Mapping(target = "costoOcultacion", source = "costo.costoOcultacion")
    AnuncioEntity toEntity(Anuncio anuncio);

    @Mapping(target = "costo.costoVisibilidad", source = "costoVisibilidad")
    @Mapping(target = "costo.costoOcultacion", source = "costoOcultacion")
    Anuncio toAnuncio(AnuncioEntity anuncioEntity);

    List<Anuncio> toAnuncioList(List<AnuncioEntity> anuncioEntities);

    List<AnuncioEntity> toAnuncioEntityList(List<Anuncio> anuncios);
}
