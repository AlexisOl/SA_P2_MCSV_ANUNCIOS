package com.example.Anuncios.MaterialAnuncio.Aplicacion.Factory;

import com.example.Anuncios.Anuncio.Dominio.Anuncio;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MaterialAnuncioUrlFactory {
    String url = "https://sa-p2-img.s3.us-east-1.amazonaws.com/anuncios/";


    public MaterialAnuncio MaterialAnuncioUrlFactory(MaterialAnuncio materialAnuncio) {
        return  new MaterialAnuncio(
                materialAnuncio.getId(),
                materialAnuncio.getTexto(),

                materialAnuncio.getLinkvideo()!=null ? url+materialAnuncio.getLinkvideo(): null,
                materialAnuncio.getLinkimagen()!=null ? url+materialAnuncio.getLinkimagen(): null,
                materialAnuncio.getIdAnuncio()
        );
    }


}
