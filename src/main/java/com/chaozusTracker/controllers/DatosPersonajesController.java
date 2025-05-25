package com.chaozusTracker.controllers;

import com.chaozusTracker.dto.DatosPersonajesResponse;
import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.services.DatosPersonajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/datos_personajes")
@CrossOrigin(origins = "*")
public class DatosPersonajesController {

    @Autowired
    private DatosPersonajesService datosPersonajesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonajeById(@PathVariable Long id){
        try {
            DatosPersonajesResponse personajes = datosPersonajesService.getPersonajeById(id);
            return ResponseEntity.ok(personajes);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(406).body(e.getMessage());
        }
    }

}
