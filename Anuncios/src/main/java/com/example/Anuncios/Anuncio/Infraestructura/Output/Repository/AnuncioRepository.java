package com.example.Anuncios.Anuncio.Infraestructura.Output.Repository;

import com.example.Anuncios.Anuncio.Infraestructura.Output.Entity.AnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnuncioRepository extends JpaRepository<AnuncioEntity, UUID> {
    List<AnuncioEntity> findAllByIdCine(UUID idCine);
}
