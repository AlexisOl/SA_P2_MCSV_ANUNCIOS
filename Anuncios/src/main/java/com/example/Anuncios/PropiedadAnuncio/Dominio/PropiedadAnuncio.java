package com.example.Anuncios.PropiedadAnuncio.Dominio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PropiedadAnuncio {
    private UUID id;
    private LocalDate fecha;
}
