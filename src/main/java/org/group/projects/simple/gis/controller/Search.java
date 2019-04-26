package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.dao.fias.AddressObjectManager;
import org.group.projects.simple.gis.dao.gis2.BuildingManager;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.model.entity.gis2.Building;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Search {

    @GetMapping("/search")
    public ModelAndView searchForm(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView();
        model.setViewName("search");
        model.addObject("searchRequest", new SearchRequest());
        return model;
    }

    @PostMapping("/search")
    public ModelAndView searchSubmit(@ModelAttribute SearchRequest searchRequest) {
        BuildingManager mDao = new BuildingManager();
        List<Building> mAddressObjects = mDao.selectByFormalName(searchRequest.getContent());
        ModelAndView model = new ModelAndView();
        model.setViewName("search");
        model.addObject("searchRequest", searchRequest);
        model.addObject("searchResult", mAddressObjects.stream()
                .distinct()
                .limit(7).
                collect(Collectors.toList()));
        return model;
    }

}
