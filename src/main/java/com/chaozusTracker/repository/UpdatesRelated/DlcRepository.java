package com.chaozusTracker.repository.UpdatesRelated;

import com.chaozusTracker.models.UpdatesRelated.DLC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DlcRepository extends JpaRepository<DLC, Long> {
}
