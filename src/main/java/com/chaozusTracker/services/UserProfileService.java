package com.chaozusTracker.services;

import com.chaozusTracker.dto.UserProfileDTO;
import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.repository.characterRelated.DatosPersonajesRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final DatosPersonajesRepository datosPersonajesRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, DatosPersonajesRepository datosPersonajesRepository, DatosPersonajesRepository datosPersonajesRepository1){
        this.userProfileRepository = userProfileRepository;
        this.datosPersonajesRepository = datosPersonajesRepository1;
    }

    public UserProfile updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserId(userId);

        if(optionalUserProfile.isPresent()) {
            UserProfile profile = optionalUserProfile.get();

            if(userProfileDTO.getUserName() != null){
                profile.setUserName(userProfileDTO.getUserName());
            }
            if(userProfileDTO.getDescription() != null){
                profile.setDescription(userProfileDTO.getDescription());
            }
            if (userProfileDTO.getFavoritosIds() != null) {
                List<DatosPersonajes> nuevosFavoritos = datosPersonajesRepository.findAllById(userProfileDTO.getFavoritosIds());
                profile.setFavoritos(nuevosFavoritos);
            }

            return userProfileRepository.save(profile);

        }
        else{
            throw new RuntimeException("Perfil de usuario no encontrado");
        }
    }

    public UserProfileDTO getUserProfileDTO(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil de usuario no encontrado"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setUserName(profile.getUserName());
        dto.setDescription(profile.getDescription());
        dto.setFavoritosIds(profile.getFavoritos().stream()
                .map(DatosPersonajes::getId)
                .toList());

        return dto;
    }

    public void addFavorito(Long userId, Long personajeId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        DatosPersonajes personaje = datosPersonajesRepository.findById(personajeId)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));

        if (!profile.getFavoritos().contains(personaje)) {
            profile.getFavoritos().add(personaje);
            userProfileRepository.save(profile);
        }
    }

    public void removeFavorito(Long userId, Long personajeId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        DatosPersonajes personaje = datosPersonajesRepository.findById(personajeId)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));

        profile.getFavoritos().removeIf(p -> p.getId().equals(personaje.getId()));

        userProfileRepository.save(profile);
    }

}
