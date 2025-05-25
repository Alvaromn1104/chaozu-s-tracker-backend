package com.chaozusTracker.controllers;

import com.chaozusTracker.models.userRelated.UserProfileRelated.Favorito;
import com.chaozusTracker.services.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("{userId}")
    public Optional<Set<Favorito>> getFavoritos(@PathVariable Long userId){
        return this.favoritoService.getFavoritosByUser(userId);
    }

    @GetMapping("/{userId}/{favorito}")
    public void agregarFavorito(@PathVariable Long userId, @PathVariable Long favoritoId){
        this.favoritoService.addFavorito(userId, favoritoId);
    }

    @GetMapping("/delete")
    public void eliminarFavorito(@PathVariable Long favoritoId){
        favoritoService.removeFavorito(favoritoId);
    }
}
