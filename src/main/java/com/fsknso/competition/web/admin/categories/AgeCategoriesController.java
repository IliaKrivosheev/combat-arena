package com.fsknso.competition.web.admin.categories;

import com.fsknso.competition.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class AgeCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/";

    @GetMapping("/age-categories")
    public String showAgeCategoriesPage(Model model) {
        log.info("Opening Age Categories Page...");
        return FOLDER_PATH + "age-categories";
    }
}
