package com.fsknso.competition.web;

import com.fsknso.competition.core.User;
import com.fsknso.competition.core.repository.UserRepository;
import com.fsknso.competition.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class FrontPageController extends BaseController {

    @GetMapping(value = {"/", "/index"})
    public String indexPage(Model model) {
        log.debug("opening front indexPage...");
        return "pages/index";
    }

    @GetMapping(value = "/alerts")
    public String alertsPage(Model model) {
        log.debug("opening front alertsPage...");
        return "pages/alerts";
    }

    @GetMapping(value = "/buttons")
    public String buttonsPage(Model model) {
        log.debug("opening front buttonsPage...");
        return "pages/buttons";
    }

    @GetMapping(value = "/calendar")
    public String calendarPage(Model model) {
        log.debug("opening front calendarPage...");
        return "pages/calendar";
    }

    @GetMapping(value = "/chart")
    public String chartPage(Model model) {
        log.debug("opening front chartPage...");
        return "pages/chart";
    }

    @GetMapping(value = "/form-elements")
    public String formElementsPage(Model model) {
        log.debug("opening front formElementsPage...");
        return "pages/form-elements";
    }

    @GetMapping(value = "/form-layout")
    public String formLayoutPage(Model model) {
        log.debug("opening front formLayoutPage...");
        return "pages/form-layout";
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model) {
        log.debug("opening front profilePage...");
        return "pages/profile";
    }

    @GetMapping(value = "/settings")
    public String settingsPage(Model model) {
        log.debug("opening front settingsPage...");
        return "pages/settings";
    }

    @GetMapping(value = "/signin")
    public String signinPage(Model model) {
        log.debug("opening front signinPage...");
        return "pages/signin";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        log.debug("opening front signinPage...");
        model.addAttribute("loginError", true);
        return "pages/signin";
    }

    @GetMapping(value = "/signup")
    public String signupPage(Model model) {
        log.debug("opening front signupPage...");
        return "pages/signup";
    }

    @GetMapping(value = "/tables")
    public String tablesPage(Model model) {
        log.debug("opening front tablesPage...");
        return "pages/tables";
    }

    @GetMapping(value = "/admin-layout")
    public String adminLayoutPage(Model model) {
        log.debug("opening front adminLayoutPage...");
        return "layouts/admin-layout";
    }
}
