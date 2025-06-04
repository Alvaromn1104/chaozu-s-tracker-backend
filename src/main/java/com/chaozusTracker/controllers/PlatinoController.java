package com.chaozusTracker.controllers;

import com.chaozusTracker.models.platinoRelated.Platino;
import com.chaozusTracker.services.PlatinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platino")
@CrossOrigin(origins = "*")
public class PlatinoController {

    @Autowired
    private PlatinoService platinoService;

    @GetMapping
    public List<Platino> getAllTrophies() {
        return platinoService.getAllTrophies();
    }

}
