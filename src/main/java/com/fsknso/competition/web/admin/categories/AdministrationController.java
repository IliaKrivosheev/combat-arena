package com.fsknso.competition.web.admin.categories;

import com.fsknso.competition.core.categories.*;
import com.fsknso.competition.core.repository.categories.AgeCategoryRepository;
import com.fsknso.competition.core.repository.categories.CategoryRepository;
import com.fsknso.competition.core.repository.categories.WeightCategoryRepository;
import com.fsknso.competition.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return FOLDER_PATH + "create-new-category";
    }

    @PostMapping("/create-new-category")
    public String createNewCategoryPage(Model model, @RequestBody String requestBody) {
        log.info("Opening Categories Administration Page...");

        log.info("Receiving POST request to create a new category...");

        log.warn("requestBody: {}", requestBody);

//        // Получаем URI запроса
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//
//        // Создаем HttpHeaders и HttpEntity с телом запроса
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // Используем RestTemplate для получения тела запроса
//        RestTemplate restTemplate = new RestTemplate();
//        String requestPayload = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class).getBody();
//
//        // Здесь requestPayload содержит тело POST запроса
//        log.warn("Request payload: " + requestPayload);


        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "redirect:" + FOLDER_PATH + "administration";
    }
}
