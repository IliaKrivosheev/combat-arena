package com.fsknso.competition.core.repository.categories;

import com.fsknso.competition.core.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUuid(String uuid);
}
