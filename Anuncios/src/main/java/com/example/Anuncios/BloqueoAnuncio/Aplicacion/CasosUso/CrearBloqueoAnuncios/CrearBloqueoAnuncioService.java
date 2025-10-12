package com.example.Anuncios.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios;

import com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Input.CrearBloqueoAnunciosCineInputPort;
import com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Output.CrearBloqueoAnunciosCineOutputPort;
import com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Output.ExisteBloqueoAnuncioCIne;
import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearBloqueoAnuncioService  implements CrearBloqueoAnunciosCineInputPort {


    private CrearBloqueoAnunciosCineOutputPort crearBloqueoAnunciosCineOutputPort;
    private ExisteBloqueoAnuncioCIne existeBloqueoAnuncioCIne;

    public CrearBloqueoAnuncioService(CrearBloqueoAnunciosCineOutputPort crearBloqueoAnunciosCineOutputPort,
                                      ExisteBloqueoAnuncioCIne existeBloqueoAnuncioCIne) {
        this.crearBloqueoAnunciosCineOutputPort=crearBloqueoAnunciosCineOutputPort;
        this.existeBloqueoAnuncioCIne=existeBloqueoAnuncioCIne;
    }


    @Override
    public BloqueoAnuncio CrearBloqueoAnuncio(CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO) {

        LocalDate fechaFin = crearBloqueoAnuncioDTO.getFecha().plusDays(crearBloqueoAnuncioDTO.getCantidad_dias());

        // verificar si no existe previamente un bloqueo
        if(this.existeBloqueoAnuncioCIne.existeBloqueoAnuncioCIne(crearBloqueoAnuncioDTO.getCine(),
                                                                    crearBloqueoAnuncioDTO.getFecha(),fechaFin)) {
            throw new RuntimeException("Ya existe un bloqueo previo en esta fecha para el cine");
        }

        return this.crearBloqueoAnunciosCineOutputPort.CrearBloqueoAnuncio(
                new BloqueoAnuncio(
                        UUID.randomUUID(),
                        crearBloqueoAnuncioDTO.getFecha(),
                        fechaFin,
                        crearBloqueoAnuncioDTO.getCantidad_dias(),
                        crearBloqueoAnuncioDTO.getCine()
                )
        );
    }
}
