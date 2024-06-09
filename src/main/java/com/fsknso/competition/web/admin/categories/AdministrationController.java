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
public class AdministrationController extends BaseController {

    private static final String FOLDER_PATH = "admin/categories/";

    @GetMapping("/administration")
    public String showCategoriesAdministrationPage(Model model) {
        log.info("Opening Categories Administration Page...");
        return FOLDER_PATH + "administration";
    }
}
