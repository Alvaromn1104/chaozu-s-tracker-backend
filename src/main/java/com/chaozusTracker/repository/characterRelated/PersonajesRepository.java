package com.chaozusTracker.repository.characterRelated;

import com.chaozusTracker.models.characterRelated.Personajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajesRepository extends JpaRepository<Personajes, Long> {
}
