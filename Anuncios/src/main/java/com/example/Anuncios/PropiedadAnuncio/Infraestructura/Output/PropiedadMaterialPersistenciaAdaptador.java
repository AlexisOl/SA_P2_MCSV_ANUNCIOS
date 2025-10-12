package com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output;


import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Mapper.PropiedadAnuncioMapper;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Output.Repository.PropiedadAnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PropiedadMaterialPersistenciaAdaptador implements CrearPropiedadAnuncioOutputPort {

    private PropiedadAnuncioRepository propiedadAnuncioRepository;
    private PropiedadAnuncioMapper propiedadAnuncioMapper;

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
}
