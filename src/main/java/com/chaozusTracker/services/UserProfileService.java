package com.chaozusTracker.services;

import com.chaozusTracker.dto.UserProfileDTO;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
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

            return userProfileRepository.save(profile);

        }
        else{
            throw new RuntimeException("Perfil de usuario no encontrado");
        }
    }

}
