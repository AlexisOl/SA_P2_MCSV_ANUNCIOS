package com.example.Anuncios.BloqueoAnuncio.Aplicacion.CasosUso.ListarBloqueoAnuncioEspecifico;

import com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Input.ListadoBloqueosCineInputPort;
import com.example.Anuncios.BloqueoAnuncio.Aplicacion.ports.Output.ListadoBloqueosCineOutputPort;
import com.example.Anuncios.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LIstarBloqueoAnunciosEspecifico implements ListadoBloqueosCineInputPort {

    private final ListadoBloqueosCineOutputPort  listadoBloqueosCineOutputPort;


    public LIstarBloqueoAnunciosEspecifico(ListadoBloqueosCineOutputPort  listadoBloqueosCineOutputPort){
        this.listadoBloqueosCineOutputPort=listadoBloqueosCineOutputPort;
    }


    @Override
    public List<BloqueoAnuncio> listadoBloqueosCine(UUID idCine) {
        //verificar existencia de cine

        return List.of();
    }
}
