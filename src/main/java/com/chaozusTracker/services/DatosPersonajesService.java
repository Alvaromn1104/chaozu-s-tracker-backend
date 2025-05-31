package com.chaozusTracker.services;

import com.chaozusTracker.dto.DatosPersonajesResponse;
import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.repository.characterRelated.DatosPersonajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class DatosPersonajesService {

    @Autowired
    private DatosPersonajesRepository datosPersonajesRepository;

    public DatosPersonajesResponse getPersonajeById(Long id) {
        Optional<DatosPersonajes> personajeOpt = datosPersonajesRepository.findWithTransformacionesById(id);

        if (personajeOpt.isPresent()) {
            DatosPersonajes datos = personajeOpt.get();

            // Combinar transformaciones directas e inversas sin duplicados
            Set<DatosPersonajes> combinadas = datos.getTransformaciones();
            if (datos.getTransformadoEn() != null) {
                for (DatosPersonajes t : datos.getTransformadoEn()) {
                    if (!combinadas.contains(t)) {
                        combinadas.add(t);
                    }
                }
            }

            return new DatosPersonajesResponse(
                    datos.getId(),
                    datos.getNombre(),
                    datos.getImagen(),
                    new ArrayList<>(combinadas),
                    datos.getHabilidades()
            );
        } else {
            throw new IllegalArgumentException("Datos no encontrados");
        }
    }

}
