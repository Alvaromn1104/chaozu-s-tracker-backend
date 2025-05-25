package com.chaozusTracker.repository.userRelated.userProfileRelated;

import com.chaozusTracker.models.userRelated.UserProfileRelated.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FavoritosRepository extends JpaRepository<Favorito, Long> {

    Optional<Set<Favorito>> findByUserId(Long userId);


}
