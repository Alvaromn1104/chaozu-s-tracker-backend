package com.chaozusTracker.repository.gameInformationRelated;

import com.chaozusTracker.models.platinoRelated.Platino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatinoRepository extends JpaRepository<Platino, Long> {
}
