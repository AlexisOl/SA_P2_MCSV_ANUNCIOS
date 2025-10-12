package com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO.VerificarCIne;

public class VerificarCineRespuestaDTO {
    private boolean existe;
    private String correlationId;

    public VerificarCineRespuestaDTO(boolean existe, String correlationId) {
        this.existe = existe;
        this.correlationId = correlationId;
    }

    public VerificarCineRespuestaDTO() {}

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
