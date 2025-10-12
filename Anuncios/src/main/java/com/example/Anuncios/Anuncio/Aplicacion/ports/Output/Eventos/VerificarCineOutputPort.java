package com.example.Anuncios.Anuncio.Aplicacion.ports.Output.Eventos;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VerificarCineOutputPort {
    CompletableFuture<Boolean> verificarCine(UUID idCine);
}
