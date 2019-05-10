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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            searchResult.addResult(new SearchResult.Result(building.getLon(), building.getLat(), building.getAddress()));
            searchResult.setContent(searchResult.getContent());

            model.addObject("searchResult", searchResult);
        }

        model.addObject("searchRequest", searchRequest);

        return model;
    }

    @RequestMapping(value = {
            "/getAddressObjects",
            "/{type}/getAddressObjects"
    }, method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getTags(@PathVariable("type") Optional<String> searchType, @RequestParam String street) {
        List<Building> buildings = service.getBuildings(street, 7);
        SearchResult result = new SearchResult();

        result.setResults(buildings.stream()
                .map(eachBuilding -> {
                    return new SearchResult.Result(eachBuilding.getLon(),
                            eachBuilding.getLat(), eachBuilding.getAddress());
                })
                .collect(Collectors.toList()));
        result.setContent(street);

        return result;
    }
}
