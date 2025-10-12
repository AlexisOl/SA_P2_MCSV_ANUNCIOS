package com.example.Anuncios.BloqueoAnuncio.Infraestructura.Input.rest.Mapper;

import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Anuncios.BloqueoAnuncio.Infraestructura.Input.rest.Model.ResponseBloqueoAnuncioDTO;
import com.example.Anuncios.BloqueoAnuncio.Infraestructura.Output.Entity.BloqueoAnuncioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BloqueoAnuncioRestMapper {
    ResponseBloqueoAnuncioDTO toResponseBloqueoAnuncioDto(BloqueoAnuncio  bloqueoAnuncio);
}
