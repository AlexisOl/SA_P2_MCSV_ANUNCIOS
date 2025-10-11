package com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.CrearAnuncioInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnunciosCineInputPort;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Mapper.AnuncioRestMapper;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model.ResponseAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio.CrearMaterialAnuncioDTO;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input.CrearMaterialAnuncioInputPort;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest.Mapper.MaterialAnuncioRestMapper;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Input.rest.Model.ResponseMaterialAnuncioDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/materialAnuncio")
public class MaterialAnuncioRestAdapter {

    private final CrearMaterialAnuncioInputPort crearMaterialAnuncioInputPort;


    private final MaterialAnuncioRestMapper materialAnuncioRestMapper;
    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseMaterialAnuncioDTO> crearMaterialAnuncio(
            @RequestPart("crearAnuncioDTO") CrearAnuncioDTO crearAnuncioDTO,
            @RequestPart(value = "texto", required = false) String texto,
            @RequestPart(value = "linkimagen", required = false) MultipartFile linkimagen,
            @RequestPart(value = "linkvideo", required = false) MultipartFile linkvideo
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materialAnuncioRestMapper.toReponseMaterialAnuncio(
                        crearMaterialAnuncioInputPort.crearMaterialAnuncio(crearAnuncioDTO, texto, linkimagen, linkvideo)
                ));
    }


}
