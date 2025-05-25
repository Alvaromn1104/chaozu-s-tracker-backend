package com.chaozusTracker.models;

import com.chaozusTracker.models.GameInformationRelated.Controles;
import com.chaozusTracker.models.GameInformationRelated.Historia;
import com.chaozusTracker.models.GameInformationRelated.Platino;
import com.chaozusTracker.models.UpdatesRelated.DLC;
import com.chaozusTracker.models.UpdatesRelated.Noticias;
import com.chaozusTracker.models.characterRelated.Personajes;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "categorias")
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Noticias> noticias = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Historia> historia = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Personajes> personajes = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Controles> controles = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Platino> platino = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<DLC> dlc = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Noticias> getNoticias() {
        return noticias;
    }

    public void setNoticias(Set<Noticias> noticias) {
        this.noticias = noticias;
    }

    public Set<Historia> getHistoria() {
        return historia;
    }

    public void setHistoria(Set<Historia> historia) {
        this.historia = historia;
    }


    public Set<Personajes> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personajes> personajes) {
        this.personajes = personajes;
    }

    public Set<Controles> getControles() {
        return controles;
    }

    public void setControles(Set<Controles> controles) {
        this.controles = controles;
    }

    public Set<Platino> getPlatino() {
        return platino;
    }

    public void setPlatino(Set<Platino> platino) {
        this.platino = platino;
    }

    public Set<DLC> getDlc() {
        return dlc;
    }

    public void setDlc(Set<DLC> dlc) {
        this.dlc = dlc;
    }
}
