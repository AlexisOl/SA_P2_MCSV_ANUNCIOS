package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest;


import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Infraestructura.Input.rest.Model.ResponseAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio.CrearPropiedadAnuncioDTO;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.CrearPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.ExisteAunciosActualesCIneInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.GenerarBloqueoInputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Mapper.PropiedadAnuncioRestMapper;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Input.rest.Model.ResponsePropiedadAnuncioDTO;
import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/propiedadAnuncio")
public class PropiedadAnuncioRestAdaptador {

    private final CrearPropiedadAnuncioInputPort  crearPropiedadAnuncioInputPort;
    private final ExisteAunciosActualesCIneInputPort existeAunciosActualesCIneInputPort;
    private final PropiedadAnuncioRestMapper  propiedadAnuncioRestMapper;
    private final GenerarBloqueoInputPort generarBloqueoInputPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponsePropiedadAnuncioDTO> crearAnuncio(@Valid @RequestBody  CrearPropiedadAnuncioDTO crearPropiedadAnuncioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.propiedadAnuncioRestMapper.toResponsePropiedadAnuncioDto(
                        crearPropiedadAnuncioInputPort.crearPropiedadAnuncio(crearPropiedadAnuncioDTO)
                ));
    }



    @GetMapping("/anunciosActuales/{fecha_inicio}/{fecha_fin}")
    @Transactional
    public List<PropiedadAnuncio> anunciosActuales(@PathVariable("fecha_inicio") LocalDate fecha_inicio, @PathVariable("fecha_fin") LocalDate fecha_fin) {
        return (this.existeAunciosActualesCIneInputPort.listaAnunciosActuales(fecha_inicio, fecha_fin)) ;
    }

    @PostMapping("/GenerarBloqueo")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void GenerarBloqueo(@Valid @RequestBody BloqueoCineDTO crearPropiedadAnuncioDTO) {
                        generarBloqueoInputPort.generarBloqueo(crearPropiedadAnuncioDTO);
    }

}
