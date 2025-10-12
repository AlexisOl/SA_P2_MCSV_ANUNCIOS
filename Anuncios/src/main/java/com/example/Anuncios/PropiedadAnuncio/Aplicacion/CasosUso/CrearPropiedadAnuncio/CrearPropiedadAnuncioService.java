package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio;


import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ExisteAnuncioIdOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.CrearPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearPropiedadAnuncioService implements CrearPropiedadAnuncioInputPort {

    // este sera sobre un anuncio ya existente
    private final CrearPropiedadAnuncioOutputPort crearPropiedadAnuncioOutputPort;
    private final ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort;
    private final ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort;

    public CrearPropiedadAnuncioService(CrearPropiedadAnuncioOutputPort crearPropiedadAnuncioOutputPort,
                                        ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort,
                                        ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort) {
        this.crearPropiedadAnuncioOutputPort=  crearPropiedadAnuncioOutputPort;
        this.existeAnuncioIdOutputPort = existeAnuncioIdOutputPort;
        this.listarAnuncioEspecificoInputPort=listarAnuncioEspecificoInputPort;
    }


    @Override
    public PropiedadAnuncio crearPropiedadAnuncio(CrearPropiedadAnuncioDTO crearPropiedadAnuncioDTO) {
        //buscar usuario - ver bien eso de la comunicaicon

        // ver si el usuario tiene la cantidad adecuada de dinero para descontar


        // ver lo de anuncio existente
        if(!this.existeAnuncioIdOutputPort.existeAnuncio(crearPropiedadAnuncioDTO.getAnuncio())){
            throw new IllegalArgumentException("El anuncio no existe en el sistema");
        }

        Anuncio anuncio =this.listarAnuncioEspecificoInputPort.listarAnuncioEspecifico(crearPropiedadAnuncioDTO.getAnuncio());

        // en base a la fechha se le asigna cuando vence
        LocalDate fechaFin= LocalDate.now();
        switch (VigenciaAnuncio.valueOf(crearPropiedadAnuncioDTO.getVigencia())) {
            case VigenciaAnuncio.DIA_1:
                fechaFin =fechaFin.plusDays(1);
                break;

            case VigenciaAnuncio.DIA_3:
                fechaFin =fechaFin.plusDays(3);

                break;
            case VigenciaAnuncio.SEMANA_1:
                fechaFin =fechaFin.plusWeeks(1);

                break;
            case VigenciaAnuncio.SEMANA_2:
                fechaFin =fechaFin.plusWeeks(2);

                break;

        }

        return this.crearPropiedadAnuncioOutputPort.crearPropiedadAnuncio(
                new  PropiedadAnuncio(
                        UUID.randomUUID(),
                        LocalDate.now(),
                        fechaFin,
                        crearPropiedadAnuncioDTO.getUsuario(),
                        anuncio,
                        VigenciaAnuncio.valueOf(crearPropiedadAnuncioDTO.getVigencia())
                )
        );
    }
}
