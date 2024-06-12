package com.combat.arena.web.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Slf4j
@Controller
public class BaseController {
    protected String errorPage(Model model) {
        return errorPage(model, "Something went wrong");
    }

    protected String errorPage(Model model, String errorMsg) {
        log.warn("Returning error page, message: {}", errorMsg);
        model.addAttribute("exception", errorMsg);
        return "error";
    }

    protected String doRedirect(Model model, String url) {
        model.asMap().clear();
        return "redirect:" + url;
    }
}
