package com.example.Anuncios.PropiedadAnuncio.Aplicacion.CasosUso.CrearPropiedadAnuncio;


import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.ListarAnuncioEspecificoInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ExisteAnuncioIdOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Input.CrearPropiedadAnuncioInputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearFactura.GenerarFacturaOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.CrearPropiedadAnuncioOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Aplicacion.ports.Output.VerificarSaldoCineOutputPort;
import com.example.Anuncios.PropiedadAnuncio.Dominio.EstadoAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.PropiedadAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Dominio.VigenciaAnuncio;
import com.example.Anuncios.PropiedadAnuncio.Infraestructura.Kafka.DTO.AnuncioCreadoDTO;
import com.example.comun.DTO.FacturaAnuncio.RespuestaFacturaAnuncioCreadaDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final GenerarFacturaOutputPort generarFacturaOutputPort;
    public CrearPropiedadAnuncioService(CrearPropiedadAnuncioOutputPort crearPropiedadAnuncioOutputPort,
                                        ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort,
                                        ListarAnuncioEspecificoInputPort listarAnuncioEspecificoInputPort,
                                        VerificarSaldoCineOutputPort verificarSaldoCineOutputPort,
                                        GenerarFacturaOutputPort generarFacturaOutputPort) {
        this.crearPropiedadAnuncioOutputPort=  crearPropiedadAnuncioOutputPort;
        this.existeAnuncioIdOutputPort = existeAnuncioIdOutputPort;
        this.listarAnuncioEspecificoInputPort=listarAnuncioEspecificoInputPort;
        this.verificarSaldoCineOutputPort=verificarSaldoCineOutputPort;
        this.generarFacturaOutputPort=generarFacturaOutputPort;
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
               propiedadAnuncioId,
                LocalDate.now(),
                fechaFin,
                crearPropiedadAnuncioDTO.getUsuario(),
                anuncio,
                VigenciaAnuncio.valueOf(crearPropiedadAnuncioDTO.getVigencia()),
                EstadoAnuncio.PENDIENTE
        );


        this.crearPropiedadAnuncioOutputPort.crearPropiedadAnuncio(
            propiedadAnuncio
        );
        //genreacion de costo general
        Double costoAnuncio =ChronoUnit.DAYS.between(
                propiedadAnuncio.getFecha(), propiedadAnuncio.getFechaFin()
        )*
                propiedadAnuncio.getAnuncio().getCosto().getCostoVisibilidad();

        // en base a esto se le agregara la cantidad de dinero en el cine
        com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO evento = new com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO();

      //  evento.setIdCine(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        evento.setAnuncioId(propiedadAnuncioId);

        evento.setCosto(
                propiedadAnuncio.getAnuncio().getCosto().getCostoVisibilidad()
        );
        evento.setCorrelationId(UUID.randomUUID().toString());


        // generacion de evento

//        this.verificarSaldoCineOutputPort.publicarAnuncioCreado(
//                evento
//        );


        System.out.println(propiedadAnuncio.getFecha()+ "   "+ propiedadAnuncio.getFechaFin());

        // aca se genera la factura
        com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO eventoFactura = new com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO();
        eventoFactura.setAnuncioId(propiedadAnuncioId);
        eventoFactura.setCosto(
                propiedadAnuncio.getAnuncio().getCosto().getCostoVisibilidad()
        );
        eventoFactura.setCorrelationId(UUID.randomUUID().toString());
        eventoFactura.setUsuarioId((crearPropiedadAnuncioDTO.getUsuario()));
        eventoFactura.setFechainicio(propiedadAnuncio.getFecha());
        eventoFactura.setFechafin(propiedadAnuncio.getFechaFin());

        this.generarFacturaOutputPort.generarFactura(eventoFactura);


        return propiedadAnuncio;
    }




}
