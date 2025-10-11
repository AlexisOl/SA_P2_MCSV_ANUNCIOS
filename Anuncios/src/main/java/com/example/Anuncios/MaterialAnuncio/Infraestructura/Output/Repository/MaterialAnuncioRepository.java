package com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Repository;

import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Entity.MaterialAnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialAnuncioRepository extends JpaRepository<MaterialAnuncioEntity, UUID> {
}
