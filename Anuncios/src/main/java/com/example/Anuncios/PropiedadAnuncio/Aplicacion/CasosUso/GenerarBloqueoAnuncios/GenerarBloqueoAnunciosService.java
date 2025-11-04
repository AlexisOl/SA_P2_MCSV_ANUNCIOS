package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.GenerarBloqueoAnuncios;

import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.ExisteAunciosActualesCIneInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.GenerarBloqueoInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EnvioPeticionBloqueoOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.GenerarBloqueoOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;
import com.example.comun.DTO.BloqueoAnuncios.PeticionBloqueoAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerarBloqueoAnunciosService implements GenerarBloqueoInputPort {
    private final ExisteAunciosActualesCIneInputPort  existeAunciosActualesCIneInputPort;
    private final EnvioPeticionBloqueoOutputPort generarBloqueoOutputPort;

    public GenerarBloqueoAnunciosService(ExisteAunciosActualesCIneInputPort  existeAunciosActualesCIneInputPort,
                                         EnvioPeticionBloqueoOutputPort generarBloqueoOutputPort){
        this.existeAunciosActualesCIneInputPort=existeAunciosActualesCIneInputPort;
        this.generarBloqueoOutputPort=generarBloqueoOutputPort;
    }
    @Override
    public void generarBloqueo(BloqueoCineDTO peticion) {
        LocalDate fechaFin = peticion.getFecha().plusDays(peticion.getCantidad_dias());

        List<PropiedadAnuncio> listado = this.existeAunciosActualesCIneInputPort.listaAnunciosActuales(peticion.getFecha(), fechaFin);
        List<PeticionBloqueoAnuncio> listadoAnunciosPeticion = new ArrayList<>();


        for (PropiedadAnuncio propiedadAnuncio : listado) {
            PeticionBloqueoAnuncio peticionAnuncio = new PeticionBloqueoAnuncio();
            peticionAnuncio.setAnuncio(propiedadAnuncio.getId());
            peticionAnuncio.setUsuario(propiedadAnuncio.getUsuario());
            peticionAnuncio.setFechafin(propiedadAnuncio.getFechaFin());
            peticionAnuncio.setFechainicio(propiedadAnuncio.getFecha());
            listadoAnunciosPeticion.add(peticionAnuncio);
        }

        //evnio de evento
        BloqueoCineDTO nuevoBloqueo =  new BloqueoCineDTO();
        nuevoBloqueo.setFecha(peticion.getFecha());
        nuevoBloqueo.setCantidad_dias(peticion.getCantidad_dias());
        nuevoBloqueo.setCine(peticion.getCine());
        nuevoBloqueo.setPeticiones(listadoAnunciosPeticion);
        this.generarBloqueoOutputPort.generarBloqueo(nuevoBloqueo);


    }
}
