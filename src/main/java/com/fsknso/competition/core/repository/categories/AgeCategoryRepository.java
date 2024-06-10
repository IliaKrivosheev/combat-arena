package com.fsknso.competition.core.repository.categories;

import com.fsknso.competition.core.categories.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
    AgeCategory findByUuid(String uuid);
}
