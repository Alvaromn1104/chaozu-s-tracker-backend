package com.chaozusTracker.repository.gameInformationRelated;

import com.chaozusTracker.models.GameInformationRelated.Controles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlesRepository extends JpaRepository<Controles, Long> {
}
