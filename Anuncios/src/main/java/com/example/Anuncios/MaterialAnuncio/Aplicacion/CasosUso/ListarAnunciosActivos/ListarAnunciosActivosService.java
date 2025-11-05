package com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.ListarAnunciosActivos;

import com.example.Anuncios.MaterialAnuncio.Aplicacion.Factory.MaterialAnuncioUrlFactory;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input.ListarAnunciosGlobalesInputPort;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output.ListarAnunciosGlobalesOutputPort;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarAnunciosActivosService implements ListarAnunciosGlobalesInputPort {
    private final ListarAnunciosGlobalesOutputPort listarAnunciosGlobalesOutputPort;
    private final MaterialAnuncioUrlFactory materialAnuncioUrlFactory;

    public ListarAnunciosActivosService(ListarAnunciosGlobalesOutputPort listarAnunciosGlobalesOutputPort,
                                        MaterialAnuncioUrlFactory materialAnuncioUrlFactory){
        this.listarAnunciosGlobalesOutputPort=listarAnunciosGlobalesOutputPort;
        this.materialAnuncioUrlFactory=materialAnuncioUrlFactory;
    }
    @Override
    public List<MaterialAnuncio> getListaMaterialAnuncios() {
        List<MaterialAnuncio> lsitados = this.listarAnunciosGlobalesOutputPort.getListaMaterialAnuncios();
        List<MaterialAnuncio> nuevosMaterialAnuncios = new ArrayList<>();
        for (MaterialAnuncio materialAnuncio : lsitados) {
            MaterialAnuncio anuncioFactory = this.materialAnuncioUrlFactory.MaterialAnuncioUrlFactory(materialAnuncio);
            nuevosMaterialAnuncios.add(anuncioFactory);
        }
        return  nuevosMaterialAnuncios;
    }
}
