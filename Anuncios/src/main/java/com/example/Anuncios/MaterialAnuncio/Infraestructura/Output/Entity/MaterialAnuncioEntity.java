package com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Entity;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Output.Entity.AnuncioEntity;
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
@Table(name = "material_anuncio")
public class MaterialAnuncioEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = true)
    private  String linkvideo;

    @Column( nullable = true)
    private  String texto;
    @Column( nullable = true)
    private  String linkimagen;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", referencedColumnName = "id")
    private AnuncioEntity idAnuncio;
}
