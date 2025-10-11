package com.example.Anuncios.S3.Infraestructura.Input;

import com.example.Anuncios.S3.Aplicacion.Ports.Input.SubirArchivoInputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/archivos")
@AllArgsConstructor
public class S3RestAdapatador {

    private final SubirArchivoInputPort subirArchivoInputPort;

    @PostMapping("/subir/{nombreUnico}")
    public ResponseEntity<String> upload(@RequestParam("archivo") MultipartFile file, @PathVariable(name = "nombreUnico") String nombreUnico) {
        try {
            String url = subirArchivoInputPort.subirArchivo(file,nombreUnico );
            return ResponseEntity.ok("Archivo subido exitosamente: " + url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }
    }


}
