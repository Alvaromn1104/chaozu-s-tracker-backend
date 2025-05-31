package com.chaozusTracker.controllers;

import com.chaozusTracker.dto.UserProfileDTO;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.services.UserProfileService;
import com.chaozusTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
