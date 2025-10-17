package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CompletarEstadoPropiedadAnuncio;

import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.EstadoAnuncio.CompletarPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.ActualizarEstadoAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.EliminarAnuncioOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompletarEstadoPropiedadAnuncioService implements CompletarPropiedadAnuncioInputPort {

    private final ActualizarEstadoAnuncioOutputPort estadoAnuncioOutputPort;
    private final EliminarAnuncioOutputPort eliminarAnuncioOutputPort;

    public CompletarEstadoPropiedadAnuncioService(ActualizarEstadoAnuncioOutputPort estadoAnuncioOutputPort,
                                                  EliminarAnuncioOutputPort eliminarAnuncioOutputPort) {
        this.estadoAnuncioOutputPort = estadoAnuncioOutputPort;
        this.eliminarAnuncioOutputPort = eliminarAnuncioOutputPort;
    }

    @Override
    public void completarPropiedadAnuncio(UUID propiedadAnuncioId, boolean exito, String motivoFallo) {
        if (!exito) {
            // Compensating action: delete the PropiedadAnuncio
            eliminarAnuncioOutputPort.eliminarAnuncio(propiedadAnuncioId);
            throw new IllegalStateException("PropiedadAnuncio fallido: " + motivoFallo);
        }
        // Update status to CONFIRMADO
        estadoAnuncioOutputPort.actualizarEstadoAnuncio(propiedadAnuncioId, "CONFIRMADO");
    }
}
