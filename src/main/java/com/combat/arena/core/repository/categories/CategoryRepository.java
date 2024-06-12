package com.combat.arena.core.repository.categories;

import com.combat.arena.core.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUuid(String uuid);
}
