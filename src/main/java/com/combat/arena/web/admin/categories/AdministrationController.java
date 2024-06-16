package com.combat.arena.web.admin.categories;

import com.combat.arena.core.categories.*;
import com.combat.arena.core.repository.categories.AgeCategoryRepository;
import com.combat.arena.core.repository.categories.CategoryRepository;
import com.combat.arena.core.repository.categories.WeightCategoryRepository;
import com.combat.arena.services.catrgoties.AgeCategoryService;
import com.combat.arena.services.catrgoties.CategoryService;
import com.combat.arena.services.catrgoties.WeightCategoryService;
import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class AdministrationController extends BaseController {

    private static final String FOLDER_PATH = "admin/categories/category/";
    private final CategoryService categoryService;
    private final AgeCategoryService ageCategoryService;
    private final WeightCategoryService weightCategoryService;


    public AdministrationController(CategoryService categoryService, AgeCategoryService ageCategoryService,
                                    WeightCategoryService weightCategoryService) {
        this.categoryService = categoryService;
        this.ageCategoryService = ageCategoryService;
        this.weightCategoryService = weightCategoryService;
    }

    @GetMapping({"/administration", "/"})
    public String showCategoriesAdministrationPage(Model model) {
        List<Category> categories = categoryService.getCategoriesByOrganizerUuid();
        model.addAttribute("categories", categories);
        return FOLDER_PATH + "administration";
    }

    @GetMapping("/create")
    public String showCreateNewCategoryPage(Model model) {
        log.info("Opening Categories Administration Page...");
        List<AgeCategory> ageCategories = ageCategoryService.getCategoriesByOrganizerUuid();
        List<WeightCategory> weightCategories = weightCategoryService.getCategoriesByOrganizerUuid();
        List<Grade> grades = Arrays.stream(Grade.values()).toList();
        List<Gender> genders = Arrays.stream(Gender.values()).toList();

        model.addAttribute("ageCategories", ageCategories);
        model.addAttribute("weightCategories", weightCategories);
        model.addAttribute("grades", grades);
        model.addAttribute("genders", genders);
        return FOLDER_PATH + "create-new-category";
    }

    @PostMapping("/create")
    public String createNewCategoryPage(Model model, CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        List<Category> categories = categoryService.getCategoriesByOrganizerUuid();
        model.addAttribute("categories", categories);
        return "redirect:/admin/categories/administration";
    }

    @PostMapping("/delete")
    public String deleteAgeCategory(@RequestParam(required = false) String uuid, Model model) {
        categoryService.delete(uuid);
        List<Category> categories = categoryService.getCategoriesByOrganizerUuid();
        model.addAttribute("categories", categories);
        return "redirect:/admin/categories/administration";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handlerExceptions(RuntimeException exception, Model model) {
        log.error("Error: {}", exception.getMessage());
        return errorPage(model, exception.getMessage());
    }
}
