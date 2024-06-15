package com.combat.arena.web.admin.categories;

import com.combat.arena.core.categories.AgeCategory;
import com.combat.arena.core.repository.categories.AgeCategoryRepository;
import com.combat.arena.services.catrgoties.AgeCategoryService;
import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories/age")
public class AgeCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/age/";
    private static final String AGE_CATEGORIES_ATTR = "ageCategories";

    private final AgeCategoryRepository ageCategoryRepository;
    private final AgeCategoryService ageCategoryService;

    public AgeCategoriesController(AgeCategoryRepository ageCategoryRepository,
                                   AgeCategoryService ageCategoryService) {
        this.ageCategoryRepository = ageCategoryRepository;
        this.ageCategoryService = ageCategoryService;
    }

    @GetMapping()
    public String showAgeCategoriesPage(Model model) {
        List<AgeCategory> ageCategories = ageCategoryService.getCategoriesByOrganizerUuid();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return FOLDER_PATH + "age-categories";
    }

    @GetMapping("/edit")
    public String editAgeCategoriesPage(@RequestParam String uuid, Model model) {
        log.info("Opening Edit Age Categories Page...");
        AgeCategory category = ageCategoryRepository.findByUuid(uuid);
        if(category == null) return errorPage(model);
        model.addAttribute("category", category);
        return FOLDER_PATH + "edit-age-category";
    }

    @PostMapping("/edit")
    public String editAgeCategories(@RequestParam String uuid, AgeCategory ageCategory, Model model) {
        ageCategoryService.update(ageCategory, uuid);
        List<AgeCategory> ageCategories = ageCategoryService.getCategoriesByOrganizerUuid();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return "redirect:/admin/categories/age";
    }

    @PostMapping("/create")
    public String createNewAgeCategories(AgeCategory ageCategory, Model model) {
        ageCategoryService.create(ageCategory);
        List<AgeCategory> ageCategories = ageCategoryService.getCategoriesByOrganizerUuid();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return "redirect:/admin/categories/age";
    }
    @PostMapping("/delete")
    public String deleteAgeCategory(@RequestParam(required = false) String uuid, Model model) {
        ageCategoryService.delete(uuid);
        List<AgeCategory> ageCategories = ageCategoryService.getCategoriesByOrganizerUuid();
        model.addAttribute(AGE_CATEGORIES_ATTR, ageCategories);
        return FOLDER_PATH + "age-categories";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handlerExceptions(NullPointerException exception, Model model) {
        log.error("Error: {}", exception.getMessage());
        return errorPage(model, exception.getMessage());
    }
}
