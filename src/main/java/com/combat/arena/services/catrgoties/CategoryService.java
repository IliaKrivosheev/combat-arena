package com.combat.arena.services.catrgoties;

import com.combat.arena.core.categories.*;
import com.combat.arena.core.repository.categories.AgeCategoryRepository;
import com.combat.arena.core.repository.categories.CategoryRepository;
import com.combat.arena.core.repository.categories.WeightCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.combat.arena.services.security.utils.SecurityUtils.getCurrentOrganizerUuid;

@Slf4j
@Service
public class CategoryService {
    private final AgeCategoryRepository ageCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final WeightCategoryRepository weightCategoryRepository;

    public CategoryService(AgeCategoryRepository ageCategoryRepository,
                           CategoryRepository categoryRepository,
                           WeightCategoryRepository weightCategoryRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.weightCategoryRepository = weightCategoryRepository;
    }

    public void save(Category category) {
        log.info("Save Category: {}", category);
        categoryRepository.save(category);
    }

    public void create(CategoryDTO categoryDTO) {
        log.info("Create Category with Data: {}", categoryDTO);
        Category category = convertFromDtoToEntity(categoryDTO);
        save(category);
    }

    public Category convertFromDtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        transformCategory(categoryDTO, category);
        return category;
    }

    private void transformCategory(CategoryDTO categoryDTO, Category category) {
        if (categoryDTO.getWeight() != null && !categoryDTO.getWeight().isEmpty()) {
            WeightCategory weightCategory = weightCategoryRepository.findByUuid(categoryDTO.getWeight());
            category.setWeightCategory(weightCategory);
        }
        if (categoryDTO.getAge() != null && !categoryDTO.getAge().isEmpty()) {
            AgeCategory ageCategory = ageCategoryRepository.findByUuid(categoryDTO.getAge());
            category.setAgeCategory(ageCategory);
        }
        if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) {
            category.setCategoryName(categoryDTO.getName());
        }
        if (categoryDTO.getCategoryType() != null && !categoryDTO.getCategoryType().isEmpty()) {
            category.setTeam(categoryDTO.getCategoryType().contains("team"));
            category.setType(categoryDTO.getCategoryType().contains("kata") ? CategoryType.KATA : CategoryType.KUMITE);
        }
        if(categoryDTO.getGrade() != null && !categoryDTO.getGrade().isEmpty()) {
            category.setGrade(Grade.valueOf(categoryDTO.getGrade()));
        }
        if(categoryDTO.getGender() != null && !categoryDTO.getGender().isEmpty()) {
            category.setGender(Gender.valueOf(categoryDTO.getGender()));
        }
    }

    public void update(CategoryDTO categoryDTO, String uuid) throws NullPointerException {
        log.info("Update Category. Uuid: {}, New Data: {}", uuid, categoryDTO);

        Category category = categoryRepository.findByUuid(uuid);
        if (category == null) throw new NullPointerException("Ошибка обноваления категории");

        transformCategory(categoryDTO, category);

        save(category);
    }

    public void delete(String uuid) throws NullPointerException {
        log.info("Delete Category: {}", uuid);
        if (uuid == null) throw new NullPointerException("Ошибка удаления категории");

        Category category = categoryRepository.findByUuid(uuid);
        if (category == null) throw new NullPointerException("Ошибка удаления категории");

        categoryRepository.delete(category);
    }

    public List<Category> getCategoriesByOrganizerUuid() throws NullPointerException {
        String organizerUuid = getCurrentOrganizerUuid();
        if (organizerUuid == null)
            throw new NullPointerException("Необходимо авторизоваться или указать организатора для данного пользователя!");
        return categoryRepository.findCategoriesByEventOrganizerUuid(organizerUuid);
    }
}
