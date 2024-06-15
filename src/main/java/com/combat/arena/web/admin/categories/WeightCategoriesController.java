package com.combat.arena.web.admin.categories;

import com.combat.arena.core.categories.WeightCategory;
import com.combat.arena.core.repository.categories.WeightCategoryRepository;
import com.combat.arena.services.catrgoties.WeightCategoryService;
import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/categories/weight")
public class WeightCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/weight/";
    private static final String WEIGHT_CATEGORIES_ATTR = "weightCategories";

    private final WeightCategoryRepository weightCategoryRepository;
    private final WeightCategoryService weightCategoryService;

    public WeightCategoriesController(WeightCategoryRepository weightCategoryRepository,
                                      WeightCategoryService weightCategoryService) {
        this.weightCategoryRepository = weightCategoryRepository;
        this.weightCategoryService = weightCategoryService;
    }

    @GetMapping()
    public String showWeightCategoriesPage(Model model) {
        List<WeightCategory> weightCategories = weightCategoryService.getCategoriesByOrganizerUuid();
        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return FOLDER_PATH + "weight-categories";
    }

    @GetMapping("/edit")
    public String editWeightCategoriesPage(@RequestParam String uuid, Model model) {
        log.info("Opening Edit Weight Categories Page...");
        WeightCategory category = weightCategoryRepository.findByUuid(uuid);
        if (category == null) return errorPage(model);
        model.addAttribute("category", category);
        return FOLDER_PATH + "edit-weight-category";
    }

    @PostMapping("/edit")
    public String editWeightCategories(@RequestParam String uuid, WeightCategory weightCategory, Model model) {
        weightCategoryService.update(weightCategory, uuid);

        List<WeightCategory> weightCategories = weightCategoryService.getCategoriesByOrganizerUuid();

        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return "redirect:/admin/categories/weight";
    }

    @PostMapping("/create")
    public String createNewWeightCategories(WeightCategory weightCategory, Model model) {
        weightCategoryService.create(weightCategory);

        List<WeightCategory> weightCategories = weightCategoryService.getCategoriesByOrganizerUuid();

        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return "redirect:/admin/categories/weight";
    }

    @PostMapping("/delete")
    public String deleteWeightCategory(@RequestParam(required = false) String uuid, Model model) {
        weightCategoryService.delete(uuid);

        List<WeightCategory> weightCategories = weightCategoryService.getCategoriesByOrganizerUuid();

        model.addAttribute(WEIGHT_CATEGORIES_ATTR, weightCategories);
        return FOLDER_PATH + "weight-categories";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handlerExceptions(NullPointerException exception, Model model) {
        log.error("Error: {}", exception.getMessage());
        return errorPage(model, exception.getMessage());
    }
}
