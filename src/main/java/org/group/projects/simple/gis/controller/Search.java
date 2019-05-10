package org.group.projects.simple.gis.controller;

import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.SearchResult;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.group.projects.simple.gis.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class Search {

    @Autowired
    private GeoObjectService service;

    @RequestMapping(value = {
            "/search",
            "/{type}/search"
    }, method = RequestMethod.GET)
    public ModelAndView searchForm(@PathVariable("type") Optional<String> searchType,
            @ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        model.addObject("middle", "search");

        if(searchType.isPresent()) {
            model.addObject("map", "true");
        }

        if(searchRequest.getContent() != null && !searchRequest.getContent().isEmpty()) {
            Building building = service.getBuildings(searchRequest.getContent(), 1).get(0);

            SearchResult searchResult = new SearchResult();
            searchResult.setContent(building.getAddress());

            model.addObject("lon", building.getLon());
            model.addObject("lat", building.getLat());
            model.addObject("searchResult", searchResult);
            model.addObject("address", searchRequest.getContent());
        }

        model.addObject("searchRequest", searchRequest);

        return model;
    }

    @RequestMapping(value = {
            "/getAddressObjects",
            "/{type}/getAddressObjects"
    }, method = RequestMethod.GET)
    @ResponseBody
    public List<Building> getTags(@PathVariable("type") Optional<String> searchType,
                                  @RequestParam String street) {
        List<Building> result = service.getBuildings(street, 7);
        return result;
    }
}
