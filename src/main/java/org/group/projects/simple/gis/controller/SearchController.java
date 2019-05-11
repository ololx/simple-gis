package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.SearchResult;
import org.group.projects.simple.gis.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    private SearchService service;

    @RequestMapping(value = {
            "/search",
            "/{type:[a-z]+status}/search"
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
            model.addObject("searchResult", service.getResult(searchRequest, 1));
        }

        model.addObject("searchRequest", searchRequest);

        return model;
    }

    @RequestMapping(value = {
            "/getAddressObjects",
            "/{type}/getAddressObjects"
    }, method = RequestMethod.POST)
    @ResponseBody
    public SearchResult getAddressObjects(@PathVariable("type") Optional<String> searchType,
                                @RequestParam String searchRequest) {
        return service.getResult(new SearchRequest(searchRequest), 5);
    }
}
