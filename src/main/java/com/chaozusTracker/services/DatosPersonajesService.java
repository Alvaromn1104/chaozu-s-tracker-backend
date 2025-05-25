package com.chaozusTracker.services;

import com.chaozusTracker.dto.DatosPersonajesResponse;
import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.repository.characterRelated.DatosPersonajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import java.util.Optional;

@Service
public class DatosPersonajesService {

    @Autowired
    private DatosPersonajesRepository datosPersonajesRepository;

    public DatosPersonajesResponse getPersonajeById(Long id) {
        Optional<DatosPersonajes> personaje = datosPersonajesRepository.findWithTransformacionesById(id);

        if (personaje.isPresent()) {
            DatosPersonajes datos = personaje.get();
            DatosPersonajesResponse datosPersonajes = new DatosPersonajesResponse(
                    datos.getId(),
                    datos.getNombre(),
                    datos.getImagen(),
                    datos.getTransformaciones(),
                    datos.getHabilidades()
            );
            return datosPersonajes;
        }
        else {
            throw new IllegalArgumentException("Datos no encontrados");
        }
    }

}
