package com.combat.arena.web.admin.categories;

import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class ArchiveCategoriesController extends BaseController {
    private static final String FOLDER_PATH = "admin/categories/";

    @GetMapping("/archive-categories")
    public String showArchiveCategoriesPage(Model model) {
        log.info("Opening Archive Categories Page...");
        return FOLDER_PATH + "archive-categories";
    }
}
