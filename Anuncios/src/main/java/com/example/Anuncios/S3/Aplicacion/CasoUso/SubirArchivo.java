package com.example.Anuncios.S3.Aplicacion.CasoUso;

import com.example.Anuncios.S3.Aplicacion.Ports.Input.SubirArchivoInputPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class SubirArchivo implements SubirArchivoInputPort {
    private final S3Client s3Client;
    private static final String BUCKET= "sa-p2-img";


    public SubirArchivo(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public String subirArchivo(MultipartFile file) {
        String key = "uploads/" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .acl("public-read")
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "https://" + BUCKET + ".s3.amazonaws.com/" + key;
    }
}
