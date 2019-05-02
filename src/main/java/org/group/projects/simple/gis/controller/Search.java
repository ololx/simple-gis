package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.repository.BuildingManager;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Search {

    @Autowired
    private BuildingManager manager;

    @GetMapping("/search")
    public ModelAndView searchForm(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("searchRequest", new SearchRequest());
        return model;
    }

    @PostMapping("/search")
    public ModelAndView searchSubmit(@ModelAttribute SearchRequest searchRequest) {
        List<Building> mAddressObjects = manager.selectByFormalName(searchRequest.getContent());
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("searchRequest", searchRequest);
        model.addObject("template", "search-map");
        return model;
    }

}
