package com.fsknso.competition.core.repository.categories;

import com.fsknso.competition.core.categories.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightCategoryRepository extends JpaRepository<WeightCategory, Long> {
    WeightCategory findByUuid(String uuid);
}
