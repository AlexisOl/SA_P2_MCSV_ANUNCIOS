package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Repository;

import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Entity.PropiedadAnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropiedadAnuncioRepository extends JpaRepository<PropiedadAnuncioEntity, UUID> {
}
