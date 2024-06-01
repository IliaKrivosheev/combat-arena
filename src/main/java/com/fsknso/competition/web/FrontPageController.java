package com.fsknso.competition.web;

import com.fsknso.competition.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FrontPageController extends BaseController {

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        log.debug("opening front indexPage...");
        return "front/index";
    }

    @GetMapping(value = "/alerts")
    public String alertsPage(Model model) {
        log.debug("opening front alertsPage...");
        return "front/alerts";
    }

    @GetMapping(value = "/buttons")
    public String buttonsPage(Model model) {
        log.debug("opening front buttonsPage...");
        return "front/buttons";
    }

    @GetMapping(value = "/calendar")
    public String calendarPage(Model model) {
        log.debug("opening front calendarPage...");
        return "front/calendar";
    }

    @GetMapping(value = "/chart")
    public String chartPage(Model model) {
        log.debug("opening front chartPage...");
        return "front/chart";
    }

    @GetMapping(value = "/form-elements")
    public String formElementsPage(Model model) {
        log.debug("opening front formElementsPage...");
        return "front/form-elements";
    }

    @GetMapping(value = "/form-layout")
    public String formLayoutPage(Model model) {
        log.debug("opening front formLayoutPage...");
        return "front/form-layout";
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model) {
        log.debug("opening front profilePage...");
        return "front/profile";
    }

    @GetMapping(value = "/settings")
    public String settingsPage(Model model) {
        log.debug("opening front settingsPage...");
        return "front/settings";
    }

    @GetMapping(value = "/signin")
    public String signinPage(Model model) {
        log.debug("opening front signinPage...");
        return "front/signin";
    }

    @GetMapping(value = "/signup")
    public String signupPage(Model model) {
        log.debug("opening front signupPage...");
        return "front/signup";
    }

    @GetMapping(value = "/tables")
    public String tablesPage(Model model) {
        log.debug("opening front tablesPage...");
        return "front/tables";
    }
}
