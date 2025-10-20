package com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.ListarMaterialAnuncioEspecifico;


import com.example.Anuncios.MaterialAnuncio.Aplicacion.Factory.MaterialAnuncioUrlFactory;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input.ListarMaterialAnuncioEspecificoInputPort;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output.ListarMaterialAnuncioEspecificoOutputPort;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListarMaterialEspecificoService implements ListarMaterialAnuncioEspecificoInputPort {

    private final ListarMaterialAnuncioEspecificoOutputPort  listarMaterialAnuncioEspecificoOutputPort;
    private final MaterialAnuncioUrlFactory  materialAnuncioUrlFactory;

    public ListarMaterialEspecificoService(ListarMaterialAnuncioEspecificoOutputPort  listarMaterialAnuncioEspecificoOutputPort,
                                           MaterialAnuncioUrlFactory  materialAnuncioUrlFactory){
        this.listarMaterialAnuncioEspecificoOutputPort=listarMaterialAnuncioEspecificoOutputPort;
        this.materialAnuncioUrlFactory=materialAnuncioUrlFactory;
    }

    @Override
    public MaterialAnuncio getMaterialAnuncio(UUID idMaterialAnuncio) {

        MaterialAnuncio materialAnuncio=   this.listarMaterialAnuncioEspecificoOutputPort.getMaterialAnuncio(idMaterialAnuncio);

        return this.materialAnuncioUrlFactory.MaterialAnuncioUrlFactory(materialAnuncio);

    }
}
