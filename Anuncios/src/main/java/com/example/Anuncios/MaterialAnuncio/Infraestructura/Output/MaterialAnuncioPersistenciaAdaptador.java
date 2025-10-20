package com.example.Anuncios.MaterialAnuncio.Infraestructura.Output;

import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output.CrearMaterialAnuncioOuputPort;
import com.example.Anuncios.MaterialAnuncio.Aplicacion.ports.Output.ListarMaterialAnuncioEspecificoOutputPort;
import com.example.Anuncios.MaterialAnuncio.Dominio.MaterialAnuncio;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Mapper.MaterialAnuncioMapper;
import com.example.Anuncios.MaterialAnuncio.Infraestructura.Output.Repository.MaterialAnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MaterialAnuncioPersistenciaAdaptador implements CrearMaterialAnuncioOuputPort, ListarMaterialAnuncioEspecificoOutputPort {
    private final MaterialAnuncioRepository materialAnuncioRepository;
    private final MaterialAnuncioMapper materialAnuncioMapper;

    @Override
    @Transactional
    public MaterialAnuncio crearMaterialAnuncio(MaterialAnuncio crearMaterialAnuncioDTO) {
        return this.materialAnuncioMapper.toMaterialAnuncio(
                this.materialAnuncioRepository.save(
                        this.materialAnuncioMapper.toMaterialAnuncioEntity(crearMaterialAnuncioDTO)
                )
        );
    }

    @Override
    public MaterialAnuncio getMaterialAnuncio(UUID idMaterialAnuncio) {
        return this.materialAnuncioMapper.toMaterialAnuncio(
                this.materialAnuncioRepository.getByIdAnuncio_Id(idMaterialAnuncio)
        );
    }
}
