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
    public String newFrontPage(Model model) {
        log.debug("opening front page...");
        return "front/index";
    }
}
