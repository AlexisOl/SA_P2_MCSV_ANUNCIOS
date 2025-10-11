package com.example.Anuncios.Anuncio.Infraestructura.Output;

import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.CrearAnuncioOutputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ExisteAnuncioIdOutputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ListarAnuncioEspecificoOutputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ListarAnunciosCineOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Infraestructura.Output.Entity.AnuncioEntity;
import com.example.Anuncios.Anuncio.Infraestructura.Output.Mapper.AnuncioMapper;
import com.example.Anuncios.Anuncio.Infraestructura.Output.Repository.AnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AnuncioPersitenciaAdaptador implements CrearAnuncioOutputPort, ListarAnuncioEspecificoOutputPort,
        ListarAnunciosCineOutputPort, ExisteAnuncioIdOutputPort {


    private final AnuncioRepository anuncioRepository;
    private final AnuncioMapper anuncioMapper;


    @Override
    @Transactional
    public Anuncio crearAnuncio(Anuncio anuncio) {

        AnuncioEntity entity = this.anuncioMapper.toEntity(anuncio);
        System.out.println("Entity: visibilidad=" + entity.getCostoVisibilidad() + " ocultacion=" + entity.getCostoOcultacion());


        System.out.println(anuncio.getTipo()+ " - "+ anuncio.getTitulo() +"--"+ anuncio.getCosto().getCostoVisibilidad() +" --- "+
                anuncio.getCosto().getCostoOcultacion());
        return this.anuncioMapper.toAnuncio(
                this.anuncioRepository.save(this.anuncioMapper.toEntity(anuncio))
        );
    }

    @Override
    public Anuncio listarAnuncioEspecifico(UUID id) {
        return this.anuncioMapper.toAnuncio(
                this.anuncioRepository.findById(id).get()
        );
    }


    @Override
    public List<Anuncio> listarAnunciosCine(UUID idCine) {
        return this.anuncioMapper.toAnuncioList(
                this.anuncioRepository.findAllByIdCine(idCine)
        );
    }

    @Override
    public Boolean existeAnuncio(UUID idAnuncio) {
        return this.anuncioRepository.existsById(idAnuncio);
    }
}
