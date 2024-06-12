package com.combat.arena.web;

import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FrontPageController extends BaseController {

    @GetMapping(value = {"/", "/index"})
    public String indexPage() {
        log.debug("opening front indexPage...");
        return "index";
    }

    @GetMapping(value = "/alerts")
    public String alertsPage() {
        log.debug("opening front alertsPage...");
        return "alerts";
    }

    @GetMapping(value = "/buttons")
    public String buttonsPage() {
        log.debug("opening front buttonsPage...");
        return "buttons";
    }

    @GetMapping(value = "/calendar")
    public String calendarPage() {
        log.debug("opening front calendarPage...");
        return "calendar";
    }

    @GetMapping(value = "/chart")
    public String chartPage() {
        log.debug("opening front chartPage...");
        return "chart";
    }

    @GetMapping(value = "/form-elements")
    public String formElementsPage() {
        log.debug("opening front formElementsPage...");
        return "form-elements";
    }

    @GetMapping(value = "/form-layout")
    public String formLayoutPage() {
        log.debug("opening front formLayoutPage...");
        return "form-layout";
    }

    @GetMapping(value = "/profile")
    public String profilePage() {
        log.debug("opening front profilePage...");
        return "profile";
    }

    @GetMapping(value = "/settings")
    public String settingsPage() {
        log.debug("opening front settingsPage...");
        return "settings";
    }

    @GetMapping(value = "/tables")
    public String tablesPage() {
        log.debug("opening front tablesPage...");
        return "tables";
    }

    @GetMapping(value = "/admin-layout")
    public String adminLayoutPage() {
        log.debug("opening front adminLayoutPage...");
        return "layouts/admin-layout";
    }
}
