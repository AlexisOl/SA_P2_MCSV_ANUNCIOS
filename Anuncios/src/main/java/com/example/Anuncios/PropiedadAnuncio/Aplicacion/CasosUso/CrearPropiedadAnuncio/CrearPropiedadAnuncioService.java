package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio;


import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ExisteAnuncioIdOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.CrearPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.VerificarSaldoCineOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioCreadoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class CrearPropiedadAnuncioService implements CrearPropiedadAnuncioInputPort {

    // este sera sobre un anuncio ya existente
    private final CrearPropiedadAnuncioOutputPort crearPropiedadAnuncioOutputPort;
    private final ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort;
    private final ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort;

    private final VerificarSaldoCineOutputPort verificarSaldoCineOutputPort;

    public CrearPropiedadAnuncioService(CrearPropiedadAnuncioOutputPort crearPropiedadAnuncioOutputPort,
                                        ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort,
                                        ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort,
                                        VerificarSaldoCineOutputPort verificarSaldoCineOutputPort) {
        this.crearPropiedadAnuncioOutputPort=  crearPropiedadAnuncioOutputPort;
        this.existeAnuncioIdOutputPort = existeAnuncioIdOutputPort;
        this.listarAnuncioEspecificoInputPort=listarAnuncioEspecificoInputPort;
        this.verificarSaldoCineOutputPort=verificarSaldoCineOutputPort;
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
        UUID propiedadAnuncioId = UUID.randomUUID();
        PropiedadAnuncio propiedadAnuncio = new  PropiedadAnuncio(
                UUID.randomUUID(),
                LocalDate.now(),
                fechaFin,
                crearPropiedadAnuncioDTO.getUsuario(),
                anuncio,
                VigenciaAnuncio.valueOf(crearPropiedadAnuncioDTO.getVigencia()),
                "PENDIENTE"
        );


        this.crearPropiedadAnuncioOutputPort.crearPropiedadAnuncio(
            propiedadAnuncio
        );
        // en base a esto se le agregara la cantidad de dinero en el cine




        AnuncioCreadoDTO evento = new AnuncioCreadoDTO();
        evento.setAnuncioId(propiedadAnuncioId);
        evento.setCosto(
                ChronoUnit.DAYS.between(
                        propiedadAnuncio.getFecha(), propiedadAnuncio.getFechaFin()
                )*
                        propiedadAnuncio.getAnuncio().getCosto().getCostoVisibilidad()
        );
        evento.setCorrelationId(UUID.randomUUID().toString());
        // generacion de evento
        this.verificarSaldoCineOutputPort.publicarAnuncioCreado(
                evento
        );

        return propiedadAnuncio;
    }
}
