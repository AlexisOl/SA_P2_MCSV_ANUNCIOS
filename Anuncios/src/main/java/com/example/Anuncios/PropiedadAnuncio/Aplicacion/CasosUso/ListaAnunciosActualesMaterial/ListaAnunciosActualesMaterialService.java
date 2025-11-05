package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.ListaAnunciosActualesMaterial;

import com.example.Anuncios.PropiedadAnuncio.Aplicacion.Factory.MaterialAnuncioFactory;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.ListaAnunciosActualesMaterialInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ExisteAunciosActualesCIneOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ListaAnunciosActualesMaterialOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ListaAnunciosActualesMaterialService implements ListaAnunciosActualesMaterialInputPort {

    //private final
    private final ListaAnunciosActualesMaterialOutputPort existeAunciosActualesCIneOutputPort;
    private final MaterialAnuncioFactory urlFactory;
    public ListaAnunciosActualesMaterialService( ListaAnunciosActualesMaterialOutputPort existeAunciosActualesCIneOutputPort,
                                         MaterialAnuncioFactory urlFactory){
        this.existeAunciosActualesCIneOutputPort=  existeAunciosActualesCIneOutputPort;
        this.urlFactory=urlFactory;
    }

    @Override
    public List<PropiedadAnuncio> listaAnunciosActualesMaterial(LocalDate fechainicio, LocalDate fechafin) {
        return this.existeAunciosActualesCIneOutputPort.listaAnunciosActualesMaterial(fechainicio, fechafin);
    }
}
