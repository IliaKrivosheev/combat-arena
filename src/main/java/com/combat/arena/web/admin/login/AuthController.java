package com.combat.arena.web.admin.login;

import com.combat.arena.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AuthController extends BaseController {
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
}
