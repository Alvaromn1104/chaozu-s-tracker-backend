package com.chaozusTracker.repository.userRelated.userProfileRelated;

import com.chaozusTracker.models.userRelated.UserProfileRelated.Clips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClipsRepository extends JpaRepository<Clips, Long> {



}
