package com.example.Anuncios.Anuncio.Infraestructura.Output.Entity;

import com.example.Anuncios.Anuncio.Dominio.ObjetosValor.CostosAnuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anuncio")
public class AnuncioEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String titulo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAnuncio tipo;
    @Column(name = "costo_visibilidad", nullable = false)
    private Double costoVisibilidad;
    @Column(name = "costo_ocultacion", nullable = false)
    private Double costoOcultacion;
    @Column(nullable = false)
    private Boolean activo;
    @Column(name = "id_cine", nullable = false)
    private UUID idCine ;

}
