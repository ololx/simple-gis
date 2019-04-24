package org.group.projects.simple.gis.controller;

import org.group.projects.simple.gis.dao.AddressObjectManager;
import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.entity.AddressObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

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
        mAddressObjects.stream().limit(5).collect(Collectors.toList());
        model.addObject("searchResult", mAddressObjects);
        return model;
    }

    @GetMapping("/getTags")
    public @ResponseBody List<AddressObject> getTags(@RequestParam String tagName) throws UnsupportedEncodingException {
        AddressObjectManager mDao = new AddressObjectManager();
        List<AddressObject> mAddressObjects = mDao.selectByFormalName(tagName);
        AddressObject mAddress;
        System.out.println(new String(tagName.getBytes(),"UTF-8"));
        return mAddressObjects.stream().limit(5).collect(Collectors.toList());


    }

}
