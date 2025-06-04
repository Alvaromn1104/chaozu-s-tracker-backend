package com.chaozusTracker.services;

import com.chaozusTracker.models.platinoRelated.Platino;
import com.chaozusTracker.repository.gameInformationRelated.PlatinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatinoService {

    @Autowired
    private PlatinoRepository platinoRepository;

    public List<Platino> getAllTrophies() {
        return platinoRepository.findAll();
    }

}
