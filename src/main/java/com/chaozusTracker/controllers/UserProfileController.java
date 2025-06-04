package com.chaozusTracker.controllers;

import com.chaozusTracker.dto.UserProfileDTO;
import com.chaozusTracker.services.UserProfileService;
import com.chaozusTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users_profiles")
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        UserProfileDTO userProfileDTO = userProfileService.getUserProfileDTO(userId);
        return ResponseEntity.ok(userProfileDTO);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO){
        try{
            userProfileService.updateUserProfile(userId, userProfileDTO);
            UserProfileDTO updatedDTO = userProfileService.getUserProfileDTO(userId);
            return ResponseEntity.ok(updatedDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error al actualizar el perfil de usuario");
        }
    }

    @PostMapping("/{userId}/pfp")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        try {
            userProfileService.uploadUserPfp(userId, file);
            return ResponseEntity.ok("Imagen de perfil subida correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al guardar la imagen de perfil.");
        }
    }

    @DeleteMapping("/{userId}/pfp")
    public ResponseEntity<?> deleteProfilePicture(@PathVariable Long userId) {
        try {
            userProfileService.deleteUserPfp(userId);
            return ResponseEntity.ok("Imagen de perfil eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la imagen de perfil.");
        }
    }


    @PostMapping("/{userId}/clips")
    public ResponseEntity<String> uploadUserClip(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        try {
            userProfileService.uploadUserClip(userId, file);
            return ResponseEntity.ok("Clip subido correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al guardar el clip.");
        }
    }

    @DeleteMapping("/{userId}/clips/{clipId}")
    public ResponseEntity<?> deleteClip(
            @PathVariable Long userId,
            @PathVariable Long clipId) {
        try {
            userProfileService.deleteUserClip(userId, clipId);
            return ResponseEntity.ok("Clip eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el clip: " + e.getMessage());
        }
    }


    @PatchMapping("/{userId}/favoritos/add/{personajeId}")
    public ResponseEntity<?> addFavorito(@PathVariable Long userId, @PathVariable Long personajeId) {
        try {
            userProfileService.addFavorito(userId, personajeId);
            return ResponseEntity.ok("Favorito añadido con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al añadir favorito");
        }
    }

    @PatchMapping("/{userId}/favoritos/remove/{personajeId}")
    public ResponseEntity<?> removeFavorito(@PathVariable Long userId, @PathVariable Long personajeId) {
        try {
            userProfileService.removeFavorito(userId, personajeId);
            return ResponseEntity.ok("Favorito eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar favorito");
        }
    }

    @PatchMapping("/{userId}/trofeos/add/{trofeoId}")
    public ResponseEntity<?> addTrofeoConseguido(@PathVariable Long userId, @PathVariable Long trofeoId) {
        try {
            userProfileService.addTrofeoConseguido(userId, trofeoId);
            return ResponseEntity.ok("Trofeo marcado como conseguido");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al marcar el trofeo como conseguido");
        }
    }

    @PatchMapping("/{userId}/trofeos/remove/{trofeoId}")
    public ResponseEntity<?> removeTrofeoConseguido(@PathVariable Long userId, @PathVariable Long trofeoId) {
        try {
            userProfileService.removeTrofeoConseguido(userId, trofeoId);
            return ResponseEntity.ok("Trofeo eliminado de conseguidos");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el trofeo de conseguidos");
        }
    }

}
