package com.chaozusTracker.models.userRelated.UserProfileRelated;

import com.chaozusTracker.models.characterRelated.Personajes;
import com.chaozusTracker.models.userRelated.Users;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Personajes> personajes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Personajes> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personajes> personajes) {
        this.personajes = personajes;
    }
}
