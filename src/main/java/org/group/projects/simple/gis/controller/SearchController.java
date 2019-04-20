package org.group.projects.simple.gis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController {

    @GetMapping("/")
    public String index() {
        return "redirect:/search";
    }

    @GetMapping("/search")
    public String greetingForm(Model model) {
        model.addAttribute("search", new SearchResult());
        return "search";
    }

    @PostMapping("/search")
    public String greetingSubmit(@ModelAttribute SearchResult search) {
        return "result";
    }

}
