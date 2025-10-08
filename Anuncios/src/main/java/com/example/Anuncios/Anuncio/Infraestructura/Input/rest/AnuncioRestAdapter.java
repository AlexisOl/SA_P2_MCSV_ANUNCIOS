package com.example.Anuncios.Anuncio.Infraestructura.Input.rest;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.CrearAnuncioInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnunciosCineInputPort;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Mapper.AnuncioRestMapper;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model.ResponseAnuncioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/anuncio")
public class AnuncioRestAdapter {

    private final CrearAnuncioInputPort crearAnuncioInputPort;
    private final ListarAnunciosCineInputPort listarAnunciosCineInputPort;
    private final ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort;

    private final AnuncioRestMapper anuncioRestMapper;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseAnuncioDTO> crearAnuncio(@Valid @RequestBody CrearAnuncioDTO crearAnuncioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.anuncioRestMapper.toResponseAnuncioDto(
                        crearAnuncioInputPort.crearAnuncio((crearAnuncioDTO))
                ));
    }

    @GetMapping("/cine/{id}")
    @Transactional

    public List<ResponseAnuncioDTO> listadoAnuncios(@PathVariable("id") UUID id) {
        return this.anuncioRestMapper.toResponseAnunciosDto(this.listarAnunciosCineInputPort.listarAnunciosCine(id)) ;
    }


    @GetMapping("/{id}")
    @Transactional
    public ResponseAnuncioDTO listadoAnuncioEspecifico(@PathVariable("id") UUID id) {
        return this.anuncioRestMapper.toResponseAnuncioDto(this.listarAnuncioEspecificoInputPort.listarAnuncioEspecifico(id)) ;
    }

}
