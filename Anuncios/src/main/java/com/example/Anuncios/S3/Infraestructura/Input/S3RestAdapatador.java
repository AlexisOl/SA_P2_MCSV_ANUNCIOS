package com.example.Anuncios.S3.Infraestructura.Input;

import com.example.Anuncios.S3.Aplicacion.Ports.Input.SubirArchivoInputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/archivos")
@AllArgsConstructor
public class S3RestAdapatador {

    private final SubirArchivoInputPort subirArchivoInputPort;

    @PostMapping("/subir")
    public ResponseEntity<String> upload(@RequestParam("archivo") MultipartFile file) {
        try {
            String url = subirArchivoInputPort.subirArchivo(file);
            return ResponseEntity.ok("Archivo subido exitosamente: " + url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }
    }


}
