package org.group.projects.simple.gis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping({
            "/",
            "/index"
    })
    public ModelAndView main() {
        ModelAndView model = new ModelAndView("redirect:/search");
        return model;
    }
}
