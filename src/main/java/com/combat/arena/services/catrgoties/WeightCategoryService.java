package com.combat.arena.services.catrgoties;

import com.combat.arena.core.categories.WeightCategory;
import com.combat.arena.core.repository.categories.WeightCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.combat.arena.services.security.utils.SecurityUtils.getCurrentOrganizerUuid;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeightCategoryService {
    private final WeightCategoryRepository weightCategoryRepository;

    public void save(WeightCategory weightCategory) {
        log.info("Save Weight Category: {}", weightCategory);
        weightCategoryRepository.save(weightCategory);
    }

    public void create(WeightCategory weightCategory) {
        log.info("Create Weight Category: {}", weightCategory);
        transformWeightCategory(weightCategory);
        save(weightCategory);
    }

    public void update(WeightCategory weightCategory, String uuid) throws NullPointerException {
        log.info("Update Weight Category. Uuid: {}, New Data: {}", uuid, weightCategory);

        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if(category == null) throw new NullPointerException("Ошибка обноваления категории");

        transformWeightCategory(weightCategory);

        category.setFromWeight(weightCategory.getFromWeight());
        category.setToWeight(weightCategory.getToWeight());
        category.setMinCategory(weightCategory.getMinCategory());
        category.setMaxCategory(weightCategory.getMaxCategory());
        save(category);
    }

    public void delete(String uuid) throws NullPointerException {
        log.info("Delete Weight Category: {}", uuid);
        if(uuid == null) throw new NullPointerException("Ошибка удаления категории");

        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if(category == null) throw new NullPointerException("Ошибка удаления категории");

        weightCategoryRepository.delete(category);
    }

    public List<WeightCategory> getCategoriesByOrganizerUuid() throws NullPointerException{
        String organizerUuid = getCurrentOrganizerUuid();
        if(organizerUuid == null) throw new NullPointerException("Необходимо авторизоваться или указать организатора для данного пользователя!");
        return weightCategoryRepository.findWeightCategoriesByEventOrganizerUuidOrderByFromWeight(organizerUuid);
    }

    private void transformWeightCategory(WeightCategory weightCategory) {
        log.info("Transform Weight Category: {}", weightCategory);
        if(weightCategory.getFromWeight() == null) {
            weightCategory.setFromWeight(0.);
        }
        if(weightCategory.getToWeight() == null) {
            weightCategory.setToWeight(0.);
        }
        if(weightCategory.getFromWeight().equals(0.)) {
            weightCategory.setMinCategory(true);
        }
        if(weightCategory.getToWeight().equals(0.)) {
            weightCategory.setMaxCategory(true);
        }
    }
}
