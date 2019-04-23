package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.dao.AddressObjectManager;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.AddressObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public ModelAndView searchForm(@ModelAttribute SearchRequest searchRequest) {
        ModelAndView model = new ModelAndView("search", "searchRequest", new SearchRequest());

        return model;
    }

    @PostMapping("/search")
    public ModelAndView searchSubmit(@ModelAttribute SearchRequest searchRequest) {
        AddressObjectManager mDao = new AddressObjectManager();
        List<AddressObject> mAddressObjects = mDao.selectByFormalName(searchRequest.getContent());
        AddressObject mAddress;

        ModelAndView model = new ModelAndView();
        model.setViewName("search");
        model.addObject("searchRequest", searchRequest);

        /*mAddressObjects.stream().forEach(ea -> System.out.println(ea.toString()));

        if(mAddressObjects.size() > 0) {
            mAddress = mAddressObjects.get(0);
        } else {
            mAddress = new AddressObject("2", "np");
        }*/

        model.addObject("searchResult", mAddressObjects);

        return model;
    }

}
