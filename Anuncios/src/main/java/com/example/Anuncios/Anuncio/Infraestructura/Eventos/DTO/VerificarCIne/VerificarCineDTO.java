package com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne;

import java.util.UUID;

public class VerificarCineDTO {
    private UUID idCine;
    private String correlationId;

    public VerificarCineDTO(UUID idCine, String correlationId) {
        this.idCine = idCine;
        this.correlationId = correlationId;
    }

    public VerificarCineDTO() {}

    public UUID getIdCine() {
        return idCine;
    }

    public void setIdCine(UUID idCine) {
        this.idCine = idCine;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

}
