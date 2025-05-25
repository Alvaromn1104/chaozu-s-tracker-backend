package com.chaozusTracker.services;

import com.chaozusTracker.models.characterRelated.Personajes;
import com.chaozusTracker.models.userRelated.UserProfileRelated.Favorito;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.models.userRelated.Users;
import com.chaozusTracker.repository.characterRelated.PersonajesRepository;
import com.chaozusTracker.repository.userRelated.UsersRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.FavoritosRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FavoritoService {

    @Autowired
    private FavoritosRepository favoritosRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PersonajesRepository personajesRepository;

    private List<Personajes> listaPersonajes;

    public Optional<Set<Favorito>> getFavoritosByUser(Long userId){
        return this.favoritosRepository.findByUserId(userId);
    }

    public void addFavorito(Long userId, Long personajeId){
        Optional<Users> user = usersRepository.findById(userId);

        Optional<Personajes> personaje = personajesRepository.findById(personajeId);

        if(user.isPresent() && personaje.isPresent()) {

            

            Favorito favorito = new Favorito();
            favorito.setUser(user.get());
            listaPersonajes.add(personaje.get());
            favorito.setPersonajes(listaPersonajes);
            favoritosRepository.save(favorito);
        }
    }

    public void removeFavorito(Long favoritoId) {
        favoritosRepository.deleteById(favoritoId);
    }

}
