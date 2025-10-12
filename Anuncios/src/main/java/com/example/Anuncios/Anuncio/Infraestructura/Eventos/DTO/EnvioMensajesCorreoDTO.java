package com.example.Anuncios.Anuncio.Infraestructura.Eventos.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnvioMensajesCorreoDTO {
    private String correo;
    private String mensaje;
    private String descripcion;
}
