package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.ListarAnunciosCineDia;


import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.ExisteAunciosActualesCIneInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ExisteAunciosActualesCIneOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ListarAnunciosCineDiaService implements ExisteAunciosActualesCIneInputPort {

    //private final
    private final ExisteAunciosActualesCIneOutputPort  existeAunciosActualesCIneOutputPort;

    public ListarAnunciosCineDiaService( ExisteAunciosActualesCIneOutputPort  existeAunciosActualesCIneOutputPort){
        this.existeAunciosActualesCIneOutputPort=  existeAunciosActualesCIneOutputPort;
    }

    @Override
    public List<PropiedadAnuncio> listaAnunciosActuales(LocalDate fechainicio, LocalDate fechafin) {


        return this.existeAunciosActualesCIneOutputPort.listaAnunciosActuales(fechainicio, fechafin);
    }
}
