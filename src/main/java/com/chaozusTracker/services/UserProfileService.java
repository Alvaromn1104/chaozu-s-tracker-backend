package com.chaozusTracker.services;

import com.chaozusTracker.dto.UserProfileDTO;
import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.models.platinoRelated.Platino;
import com.chaozusTracker.models.userRelated.UserProfileRelated.Clips;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.repository.characterRelated.DatosPersonajesRepository;
import com.chaozusTracker.repository.gameInformationRelated.PlatinoRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.ClipsRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final DatosPersonajesRepository datosPersonajesRepository;
    private final PlatinoRepository platinoRepository;
    private final ClipsRepository clipsRepository;
    private final CloudinaryService cloudinaryService;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              DatosPersonajesRepository datosPersonajesRepository,
                              PlatinoRepository platinoRepository,
                              ClipsRepository clipsRepository,
                              CloudinaryService cloudinaryService) {
        this.userProfileRepository = userProfileRepository;
        this.datosPersonajesRepository = datosPersonajesRepository;
        this.platinoRepository = platinoRepository;
        this.clipsRepository = clipsRepository;
        this.cloudinaryService = cloudinaryService;
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

    public void uploadUserPfp(Long userId, MultipartFile file) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        if (profile.getPfpPublicId() != null) {
            cloudinaryService.deleteFile(profile.getPfpPublicId());
        }

        var result = cloudinaryService.uploadFile(file, "pfps");
        profile.setPfp(result.getUrl());
        profile.setPfpPublicId(result.getPublicId());
        userProfileRepository.save(profile);
    }

    public void deleteUserPfp(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        if (profile.getPfpPublicId() != null) {
            cloudinaryService.deleteFile(profile.getPfpPublicId());
            profile.setPfp(null);
            profile.setPfpPublicId(null);
            userProfileRepository.save(profile);
        }
    }

    public void uploadUserClip(Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Archivo vacío");
        }

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        var result = cloudinaryService.uploadFile(file, "clips");

        Clips clip = new Clips();
        clip.setNombre(file.getOriginalFilename());
        clip.setUrl(result.getUrl());
        clip.setPublicId(result.getPublicId());
        clip.setUserProfile(profile);

        clipsRepository.save(clip);
    }

    public void deleteUserClip(Long userId, Long clipId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        Clips clip = clipsRepository.findById(clipId)
                .orElseThrow(() -> new RuntimeException("Clip no encontrado"));

        if (!clip.getUserProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("No autorizado para borrar este clip");
        }

        cloudinaryService.deleteFile(clip.getPublicId());  // elimina de Cloudinary
        clipsRepository.delete(clip);                      // elimina de tu base de datos
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

    public void addTrofeoConseguido(Long userId, Long trofeoId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        Platino trofeo = platinoRepository.findById(trofeoId)
                .orElseThrow(() -> new RuntimeException("Trofeo no encontrado"));

        if (!profile.getTrofeosConseguidos().contains(trofeo)) {
            profile.getTrofeosConseguidos().add(trofeo);
            userProfileRepository.save(profile);
        }
    }

    public void removeTrofeoConseguido(Long userId, Long trofeoId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        profile.getTrofeosConseguidos().removeIf(t -> t.getId().equals(trofeoId));

        userProfileRepository.save(profile);
    }

}
