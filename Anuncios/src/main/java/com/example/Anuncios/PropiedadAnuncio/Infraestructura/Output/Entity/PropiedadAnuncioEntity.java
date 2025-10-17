package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Entity;

import com.example.Anuncios.Anuncio.Infraestructura.Output.Entity.AnuncioEntity;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
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
@Table(name = "propiedad_anuncio")
public class PropiedadAnuncioEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private LocalDate fechaFin;
    @Column( nullable = false)
    private UUID usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anuncio", referencedColumnName = "id")
    private AnuncioEntity anuncio;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VigenciaAnuncio  vigencia;
    @Transient
    private String estado;

}
