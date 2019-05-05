package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.group.projects.simple.gis.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

        if(searchRequest.getContent() != null && !searchRequest.getContent().isEmpty()) {
            Building building = service.getBuildings(searchRequest.getContent(), 1).get(0);

            searchRequest.setContent(String.format("%s,%s-%s",
                    building.getCity(),
                    building.getStreet(),
                    building.getNumber()));

            model.addObject("lon", building.getLon());
            model.addObject("lat", building.getLat());
            model.addObject("address", searchRequest.getContent());
            model.addObject("map", "true");
        }

        model.addObject("searchRequest", searchRequest);

        return model;
    }

    @PostMapping("/search")
    @ResponseBody
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
