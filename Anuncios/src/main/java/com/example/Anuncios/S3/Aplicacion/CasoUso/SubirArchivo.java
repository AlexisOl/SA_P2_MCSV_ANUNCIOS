package com.example.Anuncios.S3.Aplicacion.CasoUso;

import com.example.Anuncios.S3.Aplicacion.Ports.Input.SubirArchivoInputPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLConnection;

@Service
public class SubirArchivo implements SubirArchivoInputPort {
    private final S3Client s3Client;
    private static final String BUCKET= "sa-p2-img";


    public SubirArchivo(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public String subirArchivo(MultipartFile file, String nombreUnico) {
        String key = "anuncios/" + nombreUnico;

        // Detectar el tipo MIME
        String contentType = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
        if (contentType == null) {
            contentType = getContentTypeByExtension(file.getOriginalFilename());
        }



        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .acl("public-read")
                .contentType(contentType)
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "https://" + BUCKET + ".s3.amazonaws.com/" + key;
    }


    private String getContentTypeByExtension(String fileName) {
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".gif")) return "image/gif";
        if (lower.endsWith(".mp4")) return "video/mp4";
        if (lower.endsWith(".mov")) return "video/quicktime";
        if (lower.endsWith(".webm")) return "video/webm";
        if (lower.endsWith(".avi")) return "video/x-msvideo";
        return "application/octet-stream";
    }
}
