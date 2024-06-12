package com.combat.arena.web.admin.categories;

import com.combat.arena.core.categories.WeightCategory;
import com.combat.arena.core.repository.categories.WeightCategoryRepository;
import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class WeightCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/weight/";
    private static final String WEIGHT_CATEGORIES_ATTR = "weightCategories";

    private final WeightCategoryRepository weightCategoryRepository;

    public WeightCategoriesController(WeightCategoryRepository weightCategoryRepository) {
        this.weightCategoryRepository = weightCategoryRepository;
    }

    @GetMapping("/weight-categories")
    public String showWeightCategoriesPage(Model model) {
        log.info("Opening Weight Categories Page...");
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();
        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return FOLDER_PATH + "weight-categories";
    }

    @GetMapping("/edit-weight-category")
    public String editWeightCategoriesPage(@RequestParam String uuid, Model model) {
        log.info("Opening Edit Weight Categories Page...");
        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if(category == null) return errorPage(model);
        model.addAttribute("category", category);
        return FOLDER_PATH + "edit-weight-category";
    }

    @PostMapping("/edit-weight-category")
    public String editWeightCategories(@RequestParam String uuid, WeightCategory weightCategory, Model model) {
        log.info("Opening Edit Weight Categories Page...");
        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if(category == null) return errorPage(model);
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
        category.setFromWeight(weightCategory.getFromWeight());
        category.setToWeight(weightCategory.getToWeight());
        category.setMinCategory(weightCategory.getMinCategory());
        category.setMaxCategory(weightCategory.getMaxCategory());
        weightCategoryRepository.save(category);
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();
        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return "redirect:/admin/categories/weight-categories";
    }

    @PostMapping("/create-weight-categories")
    public String createNewWeightCategories(WeightCategory weightCategory, Model model) {
        log.info("Create New Weight Category...");
        log.info("weightCategory: {}", weightCategory);
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
        weightCategoryRepository.save(weightCategory);
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();
        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return "redirect:/admin/categories/weight-categories";
    }
    @PostMapping("/delete-weight-category")
    public String deleteWeightCategory(@RequestParam(required = false) String uuid, Model model) {
        if(uuid == null) return "error";
        log.info("Delete Weight Category...");
        log.info("uuid: {}", uuid);
        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if(category == null) return "error";
        weightCategoryRepository.delete(category);

        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();
        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return FOLDER_PATH + "weight-categories";
    }
}
