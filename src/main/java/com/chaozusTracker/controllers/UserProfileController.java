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
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) {
        UserProfile userProfile = userService.getUserProfileByUserId(userId);
        return ResponseEntity.ok(userProfile);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO){
        try{
            UserProfile updatedProfile = userProfileService.updateUserProfile(userId, userProfileDTO);
            return ResponseEntity.ok(updatedProfile);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error al actualizar el perfil de usuario");
        }
    }

}
