package com.chaozusTracker.dto;

import com.chaozusTracker.models.userRelated.UserProfileRelated.Rango;

import java.util.List;

public class UserProfileDTO {

    private Long id;
    private String userName;
    private String description;
    private List<Long> favoritosIds;
    private List<Long> trofeosConseguidosIds;
    private Rango rango;
    private String pfp;
    private List<ClipDTO> clips;

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

    public List<Long> getFavoritosIds() {
        return favoritosIds;
    }

    public void setFavoritosIds(List<Long> favoritosIds) {
        this.favoritosIds = favoritosIds;
    }
    public List<Long> getTrofeosConseguidosIds() {
        return trofeosConseguidosIds;
    }

    public void setTrofeosConseguidosIds(List<Long> trofeosConseguidosIds) {
        this.trofeosConseguidosIds = trofeosConseguidosIds;
    }

    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public List<ClipDTO> getClips() {
        return clips;
    }

    public void setClips(List<ClipDTO> clips) {
        this.clips = clips;
    }
}
