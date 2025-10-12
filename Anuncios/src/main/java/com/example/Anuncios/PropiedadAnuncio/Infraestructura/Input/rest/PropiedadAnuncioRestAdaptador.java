package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest;


import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model.ResponseAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio.CrearPropiedadAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.CrearPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Mapper.PropiedadAnuncioRestMapper;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Model.ResponsePropiedadAnuncioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/propiedadAnuncio")
public class PropiedadAnuncioRestAdaptador {

    private final CrearPropiedadAnuncioInputPort  crearPropiedadAnuncioInputPort;
    private final PropiedadAnuncioRestMapper  propiedadAnuncioRestMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponsePropiedadAnuncioDTO> crearAnuncio(@Valid @RequestBody  CrearPropiedadAnuncioDTO crearPropiedadAnuncioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.propiedadAnuncioRestMapper.toResponsePropiedadAnuncioDto(
                        crearPropiedadAnuncioInputPort.crearPropiedadAnuncio(crearPropiedadAnuncioDTO)
                ));
    }

}
