package com.chaozusTracker.services;


import com.chaozusTracker.repository.userRelated.userProfileRelated.ClipsRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ClipsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ClipsRepository clipsRepository;


    public void removeClip(Long idClip){
         this.clipsRepository.deleteById(idClip);
    }

}
