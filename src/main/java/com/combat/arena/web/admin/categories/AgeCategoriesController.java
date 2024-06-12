package com.combat.arena.web.admin.categories;

import com.combat.arena.core.categories.AgeCategory;
import com.combat.arena.core.repository.categories.AgeCategoryRepository;
import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class AgeCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/age/";
    private static final String AGE_CATEGORIES_ATTR = "ageCategories";

    private final AgeCategoryRepository ageCategoryRepository;

    public AgeCategoriesController(AgeCategoryRepository ageCategoryRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
    }

    @GetMapping("/age-categories")
    public String showAgeCategoriesPage(Model model) {
        log.info("Opening Age Categories Page...");
        List<AgeCategory> ageCategories = ageCategoryRepository.findAll();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return FOLDER_PATH + "age-categories";
    }

    @GetMapping("/edit-age-category")
    public String editAgeCategoriesPage(@RequestParam String uuid, Model model) {
        log.info("Opening Edit Age Categories Page...");
        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) return errorPage(model);
        model.addAttribute("category", category);
        return FOLDER_PATH + "edit-age-category";
    }

    @PostMapping("/edit-age-category")
    public String editAgeCategories(@RequestParam String uuid, AgeCategory ageCategory, Model model) {
        log.info("Opening Edit Age Categories Page...");
        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) return errorPage(model);
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
        category.setName(ageCategory.getName());
        category.setFromAge(ageCategory.getFromAge());
        category.setToAge(ageCategory.getToAge());
        category.setMinCategory(ageCategory.getMinCategory());
        category.setMaxCategory(ageCategory.getMaxCategory());
        ageCategoryRepository.save(category);
        List<AgeCategory> ageCategories = ageCategoryRepository.findAll();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return "redirect:/admin/categories/age-categories";
    }

    @PostMapping("/create-age-categories")
    public String createNewAgeCategories(AgeCategory ageCategory, Model model) {
        log.info("Create New Age Category...");
        log.info("ageCategory: {}", ageCategory);
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
        ageCategoryRepository.save(ageCategory);
        List<AgeCategory> ageCategories = ageCategoryRepository.findAll();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return "redirect:/admin/categories/age-categories";
    }
    @PostMapping("/delete-age-category")
    public String deleteAgeCategory(@RequestParam(required = false) String uuid, Model model) {
        if(uuid == null) return "error";
        log.info("Delete Age Category...");
        log.info("uuid: {}", uuid);
        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) return "error";
        ageCategoryRepository.delete(category);

        List<AgeCategory> ageCategories = ageCategoryRepository.findAll();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return FOLDER_PATH + "age-categories";
    }
}
