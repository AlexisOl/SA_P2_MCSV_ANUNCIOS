package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio;

import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio.CrearMaterialAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrearAnuncioDTO {
    private String titulo;
    private String tipo;
    private Double costoVisibilidad;
    private Double costoOcultacion;

    private Boolean activo;
    private UUID idCine ;


}
