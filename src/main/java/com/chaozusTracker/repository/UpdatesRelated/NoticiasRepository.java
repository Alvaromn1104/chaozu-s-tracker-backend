package com.chaozusTracker.repository.UpdatesRelated;

import com.chaozusTracker.models.UpdatesRelated.Noticias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, Long> {
}
