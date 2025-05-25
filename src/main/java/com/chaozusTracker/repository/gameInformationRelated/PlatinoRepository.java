package com.chaozusTracker.repository.gameInformationRelated;

import com.chaozusTracker.models.GameInformationRelated.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatinoRepository extends JpaRepository<Historia, Long> {
}
