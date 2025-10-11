package com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio.CrearMaterialAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import org.springframework.web.multipart.MultipartFile;

public interface CrearMaterialAnuncioInputPort {
    MaterialAnuncio crearMaterialAnuncio(CrearAnuncioDTO crearMaterialAnuncioDTO, String texto, MultipartFile linkimagen, MultipartFile linkvideo);
}
