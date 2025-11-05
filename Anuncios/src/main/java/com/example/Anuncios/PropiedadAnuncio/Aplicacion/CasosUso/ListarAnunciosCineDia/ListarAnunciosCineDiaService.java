package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.ListarAnunciosCineDia;


import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.Factory.MaterialAnuncioFactory;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.ExisteAunciosActualesCIneInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ExisteAunciosActualesCIneOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAnunciosCineDiaService implements ExisteAunciosActualesCIneInputPort {

    //private final
    private final ExisteAunciosActualesCIneOutputPort  existeAunciosActualesCIneOutputPort;
    private final MaterialAnuncioFactory urlFactory;
    public ListarAnunciosCineDiaService( ExisteAunciosActualesCIneOutputPort  existeAunciosActualesCIneOutputPort,
                                         MaterialAnuncioFactory urlFactory){
        this.existeAunciosActualesCIneOutputPort=  existeAunciosActualesCIneOutputPort;
        this.urlFactory=urlFactory;
    }

    @Override
    public List<PropiedadAnuncio> listaAnunciosActuales(LocalDate fechainicio, LocalDate fechafin) {
        // 1. Obtener lista con Propiedad + Material (del repositorio)
        List<PropiedadAnuncio> anuncios = existeAunciosActualesCIneOutputPort.listaAnunciosActuales(fechainicio, fechafin);

        // 2. Enriquecer cada MaterialAnuncio con URLs completas
        return anuncios.stream()
                .peek(propiedad -> {
                    MaterialAnuncio material = propiedad.getMaterialAnuncio();
                    if (material != null) {
                        MaterialAnuncio enriquecido = urlFactory.enriquecerConUrls(material);
                        propiedad.setMaterialAnuncio(enriquecido);
                    }
                })
                .collect(Collectors.toList());
    }
}
