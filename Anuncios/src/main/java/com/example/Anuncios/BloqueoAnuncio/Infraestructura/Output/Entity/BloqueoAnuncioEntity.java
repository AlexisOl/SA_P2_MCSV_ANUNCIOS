package com.example.Anuncios.BloqueoAnuncio.Infraestructura.Output.Entity;

import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bloqueo_anuncio")
public class BloqueoAnuncioEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fecha_fin;
    @Column(nullable = false)
    private  Long cantidad_dias;
    @Column(name = "id_cine", nullable = false)
    private UUID idCine ;
}
