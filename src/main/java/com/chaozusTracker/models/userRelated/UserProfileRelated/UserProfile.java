package com.chaozusTracker.models.userRelated.UserProfileRelated;

import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import com.chaozusTracker.models.platinoRelated.Platino;
import com.chaozusTracker.models.userRelated.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "user_name")
    private String userName;

    @Column(length = 65534, name = "profile_description")
    private String description;

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Users user;


    @ManyToMany
    @JoinTable(
            name = "user_trophies",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "trofeo_id")
    )
    private List<Platino> trofeosConseguidos;

    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<DatosPersonajes> favoritos;

    private String pfp;

    @Column(name = "pfp_public_id")
    private String pfpPublicId;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Clips> clips;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<DatosPersonajes> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<DatosPersonajes> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Platino> getTrofeosConseguidos() {
        return trofeosConseguidos;
    }

    public void setTrofeosConseguidos(List<Platino> trofeosConseguidos) {
        this.trofeosConseguidos = trofeosConseguidos;
    }

    public String getPfp() {
        return pfp;
    }
    public String getPfpPublicId() {
        return pfpPublicId;
    }

    public void setPfpPublicId(String pfpPublicId) {
        this.pfpPublicId = pfpPublicId;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public List<Clips> getClips() {
        return clips;
    }

    public void setClips(List<Clips> clips) {
        this.clips = clips;
    }
}
