package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output;


import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.ActualizarEstadoAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.EstadoAnuncio.EliminarAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Mapper.PropiedadAnuncioMapper;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Repository.PropiedadAnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class PropiedadMaterialPersistenciaAdaptador implements CrearPropiedadAnuncioOutputPort,
        ActualizarEstadoAnuncioOutputPort,
        EliminarAnuncioOutputPort {

    private PropiedadAnuncioRepository propiedadAnuncioRepository;
    private PropiedadAnuncioMapper propiedadAnuncioMapper;
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
}
