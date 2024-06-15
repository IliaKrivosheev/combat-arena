package com.combat.arena.services.catrgoties;

import com.combat.arena.core.categories.AgeCategory;
import com.combat.arena.core.categories.Category;
import com.combat.arena.core.repository.categories.AgeCategoryRepository;
import com.combat.arena.core.repository.categories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.combat.arena.services.security.utils.SecurityUtils.getCurrentOrganizerUuid;

@Slf4j
@Service
public class CategoryService {
    private final AgeCategoryRepository ageCategoryRepository;
    private final CategoryRepository categoryRepository;

    public CategoryService(AgeCategoryRepository ageCategoryRepository,
                           CategoryRepository categoryRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public void transformAgeCategory(AgeCategory ageCategory) {
        log.info("Transform Age Category: {}", ageCategory);
        if(ageCategory.getFromAge() == null) {
            ageCategory.setFromAge(0);
        }
        if(ageCategory.getToAge() == null) {
            ageCategory.setToAge(0);
        }
        if(ageCategory.getFromAge() == 0) {
            ageCategory.setMinCategory(true);
        }
        if(ageCategory.getToAge() == 0) {
            ageCategory.setMaxCategory(true);
        }
    }

    public void save(AgeCategory ageCategory) {
        log.info("Save Age Category: {}", ageCategory);
        ageCategoryRepository.save(ageCategory);
    }

    public void create(AgeCategory ageCategory) {
        log.info("Create Age Category: {}", ageCategory);
        transformAgeCategory(ageCategory);
        save(ageCategory);
    }

    public void update(AgeCategory ageCategory, String uuid) throws NullPointerException {
        log.info("Update Age Category. Uuid: {}, New Data: {}", uuid, ageCategory);

        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) throw new NullPointerException("Ошибка обноваления категории");

        transformAgeCategory(ageCategory);

        category.setName(ageCategory.getName());
        category.setFromAge(ageCategory.getFromAge());
        category.setToAge(ageCategory.getToAge());
        category.setMinCategory(ageCategory.getMinCategory());
        category.setMaxCategory(ageCategory.getMaxCategory());
        save(category);
    }

    public void delete(String uuid) throws NullPointerException {
        log.info("Delete Age Category: {}", uuid);
        if(uuid == null) throw new NullPointerException("Ошибка удаления категории");

        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) throw new NullPointerException("Ошибка удаления категории");

        ageCategoryRepository.delete(category);
    }

    public List<Category> getCategoriesByOrganizerUuid() throws NullPointerException{
        String organizerUuid = getCurrentOrganizerUuid();
        if(organizerUuid == null) throw new NullPointerException("Необходимо авторизоваться или указать организатора для данного пользователя!");
        return categoryRepository.findCategoriesByEventOrganizerUuid(organizerUuid);
    }
}
