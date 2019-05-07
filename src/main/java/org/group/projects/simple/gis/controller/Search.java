package org.group.projects.simple.gis.controller;

import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.SearchResult;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.group.projects.simple.gis.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
/*@SessionAttributes({"searchResult"})*/
public class Search {

    @Autowired
    private GeoObjectService service;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchForm(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("middle", "search");

        if(searchRequest.getContent() != null && !searchRequest.getContent().isEmpty()) {
            Building building = service.getBuildings(searchRequest.getContent(), 10).get(0);

            SearchResult result = new SearchResult();
            result.setContent(building.getAddress());

            model.addObject("lon", building.getLon());
            model.addObject("lat", building.getLat());
            model.addObject("address", searchRequest.getContent());
            model.addObject("map", "true");
        }

        model.addObject("searchRequest", searchRequest);

        return model;
    }

    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ResponseBody
    public List<Building> getTags(@RequestParam String street) {
        List<Building> result = service.getBuildings(street, 5);
        return result;
    }
}
