package com.chaozusTracker.repository.characterRelated;

import com.chaozusTracker.models.characterRelated.DatosPersonajes;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatosPersonajesRepository extends JpaRepository<DatosPersonajes, Long> {
    @EntityGraph(attributePaths = {"transformaciones"})
    Optional<DatosPersonajes> findWithTransformacionesById(Long id);
}
