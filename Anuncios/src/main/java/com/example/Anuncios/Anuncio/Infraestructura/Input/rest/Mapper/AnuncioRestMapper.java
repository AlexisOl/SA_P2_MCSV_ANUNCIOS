package com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Mapper;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model.ResponseAnuncioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnuncioRestMapper {

    ResponseAnuncioDTO toResponseAnuncioDto(Anuncio anuncio);


    List<ResponseAnuncioDTO>  toResponseAnunciosDto(List<Anuncio> anuncios);
}
