package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.SearchResult;
import org.group.projects.simple.gis.service.SearchService;
import org.group.projects.simple.gis.service.view.SearchViewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    private SearchService service;

    @Autowired
    private SearchViewBuilder builder;

    @RequestMapping(value = {
            "/search",
            "/{type:[a-z]+}/search"
    }, method = RequestMethod.GET)
    public ModelAndView searchGeneral(@PathVariable("type") Optional<String> searchType,
                                   @ModelAttribute SearchRequest searchRequest) {
        return builder.getView()
                .addModelAttibute("map", searchType.isPresent() ? "true" : "false")
                .addModelAttibute("searchRequest", searchRequest.getContent() != null
                        ? searchRequest
                        : new SearchRequest())
                .addModelAttibute("searchResult", searchRequest.getContent() != null
                        ? service.getResult(searchRequest, 1)
                        : new SearchResult())
                .getData();
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
