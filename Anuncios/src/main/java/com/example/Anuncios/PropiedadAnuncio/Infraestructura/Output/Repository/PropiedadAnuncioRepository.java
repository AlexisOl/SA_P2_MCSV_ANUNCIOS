package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Repository;

import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Entity.PropiedadAnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PropiedadAnuncioRepository extends JpaRepository<PropiedadAnuncioEntity, UUID> {



    @Query("SELECT pa FROM PropiedadAnuncioEntity pa where (" +
            ":fecha between pa.fecha and pa.fechaFin " +
            "or :fechafin between pa.fecha and pa.fechaFin" +
            " or pa.fecha between  :fecha and :fechafin " +
            " or pa.fechaFin between  :fecha and :fechafin) " +
            "and pa.estado = 'EXITOSA'")
    List<PropiedadAnuncioEntity> listaAnunciosActuales(@Param("fecha") LocalDate fechainicio, @Param("fechafin") LocalDate fechafin);
}
