package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.dao.AddressObjectManager;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.FiasAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
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
        AddressObjectManager mDao = new AddressObjectManager();
        List<FiasAddress> mAddressObjects = mDao.selectByFormalName(searchRequest.getContent());
        FiasAddress mAddress;
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