package com.example.Anuncios.Anuncio.Aplicacion.CasosUso.ListarAnuncioEspecifico;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ListarAnuncioEspecificoOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class ListarAnuncioEspecirficoServicio implements ListarAnuncioEspecificoInputPort {
    private final ListarAnuncioEspecificoOutputPort listarAnuncioEspecificoOutputPort;


    public ListarAnuncioEspecirficoServicio(ListarAnuncioEspecificoOutputPort listarAnuncioEspecificoOutputPort){
        this.listarAnuncioEspecificoOutputPort=listarAnuncioEspecificoOutputPort;
    }
    @Override
    public Anuncio listarAnuncioEspecifico(UUID id) {
        return this.listarAnuncioEspecificoOutputPort.listarAnuncioEspecifico(id);
    }
}
