package com.combat.arena.core.repository.categories;

import com.combat.arena.core.categories.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightCategoryRepository extends JpaRepository<WeightCategory, Long> {
    WeightCategory findByUuid(String uuid);
}
