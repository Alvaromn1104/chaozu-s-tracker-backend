package com.chaozusTracker.dto;

import java.util.List;

public class UserProfileDTO {

    private String userName;
    private String description;
    private List<Long> favoritosIds;

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
}
