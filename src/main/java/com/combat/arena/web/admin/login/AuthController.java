package com.combat.arena.web.admin.login;

import com.combat.arena.core.organizer.EventOrganizer;
import com.combat.arena.services.security.CheckUserService;
import com.combat.arena.services.security.CreateEventOrganizerServiceTemp;
import com.combat.arena.services.security.CreateUserServiceTemp;
import com.combat.arena.web.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AuthController extends BaseController {
    private final CreateEventOrganizerServiceTemp createEventOrganizerServiceTemp;
    private final CreateUserServiceTemp createUserServiceTemp;
    private final CheckUserService checkUserService;

    private static final String FOLDER_PATH = "admin/auth/";
    @GetMapping("/signin")
    public String signinPage() {
        log.debug("opening front signinPage...");
        return FOLDER_PATH + "signin";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        log.debug("opening front signinPage...");
        model.addAttribute("loginError", true);
        return FOLDER_PATH + "signin";
    }

    @GetMapping("/signup")
    public String signupPage() {
        log.debug("opening front signupPage...");
        return FOLDER_PATH + "signup";
    }

    @PostMapping("/signup")
    public String createUserRedirecting(@RequestParam String email, @RequestParam String password, Model model) {
        if(!validateInputDataForRegistration(email, password)) {
            model.addAttribute("registrationError", true);
            return FOLDER_PATH + "signup";
        }
        EventOrganizer organizer = createEventOrganizerServiceTemp.getDefaultOrganizer();
        createUserServiceTemp.createUser(email, password, organizer.getUuid());
        return doRedirect(FOLDER_PATH + "signin");
    }

    private boolean validateInputDataForRegistration(String email, String password) {
        return email != null && password != null && !checkUserService.alredyHasUserByEmail(email);
    }
}
