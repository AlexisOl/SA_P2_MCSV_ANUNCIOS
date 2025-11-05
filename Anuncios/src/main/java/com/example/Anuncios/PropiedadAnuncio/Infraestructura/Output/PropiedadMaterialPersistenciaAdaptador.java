package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output;


import com.example.Anuncios.Anuncio.Infraestructura.Output.Mapper.AnuncioMapper;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Entity.MaterialAnuncioEntity;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Mapper.MaterialAnuncioMapper;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.Factory.MaterialAnuncioFactory;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CambioEstadoAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.ActualizarEstadoAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.EliminarAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ExisteAunciosActualesCIneOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.ListaAnunciosActualesMaterialOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.EstadoAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Entity.PropiedadAnuncioEntity;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Mapper.PropiedadAnuncioMapper;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Repository.PropiedadAnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PropiedadMaterialPersistenciaAdaptador implements CrearPropiedadAnuncioOutputPort,
        ActualizarEstadoAnuncioOutputPort, ExisteAunciosActualesCIneOutputPort,
        EliminarAnuncioOutputPort, CambioEstadoAnuncioOutputPort, ListaAnunciosActualesMaterialOutputPort {

    private PropiedadAnuncioRepository propiedadAnuncioRepository;
    private PropiedadAnuncioMapper propiedadAnuncioMapper;
    private MaterialAnuncioMapper materialAnuncioMapper;
    private AnuncioMapper anuncioMapper;
    private MaterialAnuncioFactory materialAnuncioFactory;
    private final ConcurrentHashMap<UUID, String> estadoCache = new ConcurrentHashMap<>();

    @Override
    public PropiedadAnuncio crearPropiedadAnuncio(PropiedadAnuncio anuncio) {

        System.out.println(anuncio.getAnuncio().getTitulo()+" --" +
                anuncio.getUsuario());
        return this.propiedadAnuncioMapper.toPropiedadAnuncio(
                this.propiedadAnuncioRepository.save(
                        this.propiedadAnuncioMapper.toPropiedadAnuncioEntity(anuncio)
                )
        );
    }

    @Override
    public void actualizarEstadoAnuncio(UUID anuncioId, String estado) {
        if (!propiedadAnuncioRepository.existsById(anuncioId)) {
            throw new IllegalArgumentException("PropiedadAnuncio no encontrado: " + anuncioId);
        }
        estadoCache.put(anuncioId, estado);
    }

    @Override
    public void eliminarAnuncio(UUID anuncioId) {
        if (!propiedadAnuncioRepository.existsById(anuncioId)) {
            throw new IllegalArgumentException("PropiedadAnuncio no encontrado: " + anuncioId);
        }
        propiedadAnuncioRepository.deleteById(anuncioId);
        estadoCache.remove(anuncioId);
    }

    @Override
    public List<PropiedadAnuncio> listaAnunciosActuales(LocalDate fechainicio, LocalDate fechafin) {
        return this.propiedadAnuncioMapper.toPropiedadAnuncios(
                this.propiedadAnuncioRepository.listaAnunciosActuales(fechainicio, fechafin)
        );
    }

    @Override
    public void cambioEstadoAnuncioOutputPort(UUID id, EstadoAnuncio estadoVenta) {
        PropiedadAnuncioEntity entidad = this.propiedadAnuncioRepository.findById(id).orElse(null);
        if (entidad == null) return;
        entidad.setEstado(estadoVenta);
        this.propiedadAnuncioRepository.save(entidad);
    }

    @Override
    public List<PropiedadAnuncio> listaAnunciosActualesMaterial(LocalDate fechainicio, LocalDate fechafin) {
        return propiedadAnuncioRepository.listaAnunciosActualesMaterial(fechainicio, fechafin).stream()
                .map(row -> {
                    PropiedadAnuncioEntity paEntity = (PropiedadAnuncioEntity) row[0];
                    MaterialAnuncioEntity mEntity = (MaterialAnuncioEntity) row[1];

                    // Mapear PropiedadAnuncio
                    PropiedadAnuncio propiedad = new PropiedadAnuncio(
                            paEntity.getId(),
                            paEntity.getFecha(),
                            paEntity.getFechaFin(),
                            paEntity.getUsuario(),
                            anuncioMapper.toAnuncio(paEntity.getAnuncio()),
                            VigenciaAnuncio.valueOf(String.valueOf(paEntity.getVigencia())),
                            EstadoAnuncio.valueOf(String.valueOf(paEntity.getEstado()))
                    );

                    // Asignar material (enriquecido)
                    if (mEntity != null) {
                        MaterialAnuncio material = materialAnuncioMapper.toMaterialAnuncio(mEntity);
                        MaterialAnuncio enriquecido = materialAnuncioFactory.enriquecerConUrls(material);
                        propiedad.setMaterialAnuncio(enriquecido);
                    }

                    return propiedad;
                })
                .collect(Collectors.toList());
    }
}
