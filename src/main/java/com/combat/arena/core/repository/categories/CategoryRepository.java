package com.combat.arena.core.repository.categories;

import com.combat.arena.core.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUuid(String uuid);
    List<Category> findCategoriesByEventOrganizerUuid(String eventOrganizerUuid);

}
