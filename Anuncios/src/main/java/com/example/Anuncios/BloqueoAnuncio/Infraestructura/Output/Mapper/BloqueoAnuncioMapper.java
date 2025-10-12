package com.example.Anuncios.BloqueoAnuncio.Infraestructura.Output.Mapper;

import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Anuncios.BloqueoAnuncio.Infraestructura.Output.Entity.BloqueoAnuncioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface BloqueoAnuncioMapper {
    BloqueoAnuncio toBloqueoAnuncio(BloqueoAnuncioEntity bloqueoAnuncioEntity);
    BloqueoAnuncioEntity toBloqueoAnuncioEntity(BloqueoAnuncio bloqueoAnuncio);
    List<BloqueoAnuncio> toBloqueoAnuncioList (List<BloqueoAnuncioEntity> bloqueoAnuncioEntity);
    List<BloqueoAnuncioEntity> toBloqueoAnuncioEntityList  (List<BloqueoAnuncio> bloqueoAnuncio);

}
