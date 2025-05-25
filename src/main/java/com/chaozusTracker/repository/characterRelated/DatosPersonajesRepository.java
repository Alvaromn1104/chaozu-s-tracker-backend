package com.chaozusTracker.repository.characterRelated;

import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosPersonajesRepository extends JpaRepository<DatosPersonajes, Long> {
}
