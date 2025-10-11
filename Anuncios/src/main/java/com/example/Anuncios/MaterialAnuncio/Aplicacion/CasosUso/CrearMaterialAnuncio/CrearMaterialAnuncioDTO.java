package com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CrearMaterialAnuncioDTO {

    private CrearAnuncioDTO crearAnuncioDTO;
}
