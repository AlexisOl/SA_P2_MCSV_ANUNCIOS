package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.ListarAnunciosCine;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnunciosCineInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ListarAnunciosCineOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarAnunciosCineServicio implements ListarAnunciosCineInputPort {

    private final ListarAnunciosCineOutputPort listarAnunciosCineOutputPort;

    public ListarAnunciosCineServicio(ListarAnunciosCineOutputPort listarAnunciosCineOutputPort){
        this.listarAnunciosCineOutputPort=listarAnunciosCineOutputPort;
    }
    @Override
    public List<Anuncio> listarAnunciosCine(UUID idCine) {
        // ver existencia de cine, aun falta
        return this.listarAnunciosCineOutputPort.listarAnunciosCine(idCine);
    }
}
