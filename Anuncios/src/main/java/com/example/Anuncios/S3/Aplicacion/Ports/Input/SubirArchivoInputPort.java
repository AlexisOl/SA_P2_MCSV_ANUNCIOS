package com.example.Anuncios.S3.Aplicacion.Ports.Input;

import org.springframework.web.multipart.MultipartFile;

public interface SubirArchivoInputPort {
    String subirArchivo(MultipartFile file, String nombreUnico) ;
}
