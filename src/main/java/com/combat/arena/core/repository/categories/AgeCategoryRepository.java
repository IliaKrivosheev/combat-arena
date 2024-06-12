package com.combat.arena.core.repository.categories;

import com.combat.arena.core.categories.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
    AgeCategory findByUuid(String uuid);
}
