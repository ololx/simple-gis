package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.service.view.SearchViewBuilder;
import org.group.projects.simple.gis.service.view.SearchViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private SearchViewBuilder builder;

    @GetMapping({
            "/",
            "/index"
    })
    public ModelAndView main() {

        return builder.getRedirectView().getData();
    }
}
