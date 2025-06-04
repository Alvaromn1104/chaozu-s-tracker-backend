package com.chaozusTracker.repository.userRelated.userProfileRelated;

import com.chaozusTracker.models.userRelated.UserProfileRelated.Clips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClipsRepository extends JpaRepository<Clips, Long> {
    List<Clips> findByUserProfileId(Long userProfileId);
}
