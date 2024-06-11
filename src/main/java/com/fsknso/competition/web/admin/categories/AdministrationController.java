package com.fsknso.competition.web.admin.categories;

import com.fsknso.competition.core.categories.*;
import com.fsknso.competition.core.repository.categories.AgeCategoryRepository;
import com.fsknso.competition.core.repository.categories.CategoryRepository;
import com.fsknso.competition.core.repository.categories.WeightCategoryRepository;
import com.fsknso.competition.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class AdministrationController extends BaseController {

    private static final String FOLDER_PATH = "admin/categories/category/";

    private final CategoryRepository categoryRepository;
    private final AgeCategoryRepository ageCategoryRepository;
    private final WeightCategoryRepository weightCategoryRepository;

    public AdministrationController(CategoryRepository categoryRepository,
                                    AgeCategoryRepository ageCategoryRepository,
                                    WeightCategoryRepository weightCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.ageCategoryRepository = ageCategoryRepository;
        this.weightCategoryRepository = weightCategoryRepository;
    }

    @GetMapping("/administration")
    public String showCategoriesAdministrationPage(Model model) {
        log.info("Opening Categories Administration Page...");
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return FOLDER_PATH + "administration";
    }

    @GetMapping("/create-new-category")
    public String showCreateNewCategoryPage(Model model) {
        log.info("Opening Categories Administration Page...");
        List<AgeCategory> ageCategories = ageCategoryRepository.findAll();
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();
        List<Grade> grades = Arrays.stream(Grade.values()).toList();
        List<Gender> genders = Arrays.stream(Gender.values()).toList();

        model.addAttribute("ageCategories", ageCategories);
        model.addAttribute("weightCategories", weightCategories);
        model.addAttribute("grades", grades);
        model.addAttribute("genders", genders);
        return "admin/categories/category/create-new-category";
    }
}
