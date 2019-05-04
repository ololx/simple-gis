package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.group.projects.simple.gis.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/*@SessionAttributes({"address"})*/
public class Search {

    @Autowired
    private GeoObjectService service;

    @GetMapping("/search")
    public ModelAndView searchForm(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("middle", "search");
        model.addObject("searchRequest", new SearchRequest());
        return model;
    }

    @PostMapping("/search")
    public ModelAndView searchSubmit(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("middle", "search");

        Building building = service.getBuildings(searchRequest.getContent(), 1).get(0);

        searchRequest.setContent(String.format("%s,%s-%s",
                building.getCity(),
                building.getStreet(),
                building.getNumber()));

        model.addObject("searchRequest", searchRequest);
        model.addObject("lon", building.getLon());
        model.addObject("lat", building.getLat());
        model.addObject("address", searchRequest.getContent());
        model.addObject("map", "true");

        return model;
    }

}
