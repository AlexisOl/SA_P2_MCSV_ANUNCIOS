package com.example.Anuncios.MaterialAnuncio.Aplicacion.CasosUso.CrearMaterialAnuncio;

import com.example.Anuncios.Anuncio.Aplicacion.CasosUso.CrearAnuncio.CrearAnuncioDTO;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Input.CrearAnuncioInputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ExisteAnuncioIdOutputPort;
import com.example.Anuncios.Anuncio.Aplicacion.ports.Output.ListarAnuncioEspecificoOutputPort;
import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.Anuncio.Dominio.TipoAnuncio;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Input.CrearMaterialAnuncioInputPort;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output.CrearMaterialAnuncioOuputPort;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.S3.Aplicacion.CasoUso.SubirArchivo;
import com.example.Anuncios.S3.Aplicacion.Ports.Input.SubirArchivoInputPort;
import com.example.Anuncios.S3.Aplicacion.Ports.Output.SubirArchivoOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class CrearMaterialAnuncioService implements CrearMaterialAnuncioInputPort {
    private final CrearMaterialAnuncioOuputPort crearMaterialAnuncioOuputPort;
    private final SubirArchivoInputPort subirArchivoInputPort;
    private final ListarAnuncioEspecificoOutputPort listarAnuncioEspecificoOutputPort;
    private final ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort;
    private final CrearAnuncioInputPort crearAnuncioInputPort;

    public CrearMaterialAnuncioService(CrearMaterialAnuncioOuputPort crearMaterialAnuncioOuputPort,
                                       SubirArchivoInputPort subirArchivoInputPort,
                                       ListarAnuncioEspecificoOutputPort listarAnuncioEspecificoOutputPort,
                                       ExisteAnuncioIdOutputPort existeAnuncioIdOutputPort,
                                       CrearAnuncioInputPort crearAnuncioInputPort){
        this.crearMaterialAnuncioOuputPort=crearMaterialAnuncioOuputPort;
        this.subirArchivoInputPort=subirArchivoInputPort;
        this.listarAnuncioEspecificoOutputPort=listarAnuncioEspecificoOutputPort;
        this.existeAnuncioIdOutputPort=existeAnuncioIdOutputPort;
        this.crearAnuncioInputPort=crearAnuncioInputPort;
    }


    @Override
    @Transactional
    public MaterialAnuncio crearMaterialAnuncio(CrearAnuncioDTO crearMaterialAnuncioDTO, String texto, MultipartFile linkimagen, MultipartFile linkvideo) {


        //crear anuncio
        Anuncio anuncio= this.crearAnuncioInputPort.crearAnuncio(crearMaterialAnuncioDTO);

     //   Anuncio obtenerAnucio= this.listarAnuncioEspecificoOutputPort.listarAnuncioEspecifico(UUID.fromString("5d352efd-553d-442b-94e1-8810bf699b19"));

//        Optional<Anuncio> obtenerAnuncioOpt = Optional.ofNullable(this.listarAnuncioEspecificoOutputPort
//                .listarAnuncioEspecifico(UUID.fromString(crearMaterialAnuncioDTO.ge)));
//
//        Anuncio obtenerAnucio = obtenerAnuncioOpt.orElseThrow(() ->
//                new RuntimeException("El anuncio con ese ID no existe"));

        // si todo bien se guarda en s3
        MaterialAnuncio nuevoMaterial = new MaterialAnuncio();

        System.out.println(anuncio.getTipo()+"---"+anuncio.getTitulo());

        switch (anuncio.getTipo()) {
            case TEXTO -> {
                nuevoMaterial.setId(UUID.randomUUID());
                nuevoMaterial.setTexto(texto);
                nuevoMaterial.setLinkvideo(null);
                nuevoMaterial.setLinkimagen(null);
                nuevoMaterial.setIdAnuncio(anuncio);


            }
            case IMAGEN ->{
                UUID idMaterial = UUID.randomUUID();
                String extension = ".png";
                String nombreUnico = idMaterial.toString() + extension;

                this.subirArchivoInputPort.subirArchivo( linkimagen,  nombreUnico );

                nuevoMaterial.setId(idMaterial);
                nuevoMaterial.setTexto(texto);
                nuevoMaterial.setLinkvideo(null);
                nuevoMaterial.setLinkimagen(nombreUnico);
                nuevoMaterial.setIdAnuncio(anuncio);

            }
            case VIDEO -> {
                UUID idMaterial = UUID.randomUUID();
                String extension = ".mp4";
                String nombreUnico = idMaterial.toString() + extension;

                this.subirArchivoInputPort.subirArchivo(linkvideo, nombreUnico);

                nuevoMaterial.setId(idMaterial);
                nuevoMaterial.setTexto(null);
                nuevoMaterial.setLinkvideo(nombreUnico);
                nuevoMaterial.setLinkimagen(null);
                nuevoMaterial.setIdAnuncio(anuncio);

            }
            case null, default ->  {
                throw new UnsupportedOperationException("No se puede crear MaterialAnuncio");
            }
        }


        // guardar en BD
   //     return null;
        return this.crearMaterialAnuncioOuputPort.crearMaterialAnuncio(
           nuevoMaterial
        );
    }
}
