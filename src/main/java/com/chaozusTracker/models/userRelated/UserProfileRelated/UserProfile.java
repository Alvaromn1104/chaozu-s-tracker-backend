package com.chaozusTracker.models.userRelated.UserProfileRelated;

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

    private List<Long> favoritos;

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

    public List<Long> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Long> favoritos) {
        this.favoritos = favoritos;
    }
}
