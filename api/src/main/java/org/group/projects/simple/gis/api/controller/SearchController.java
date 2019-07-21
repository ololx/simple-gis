package org.group.projects.simple.gis.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.api.model.entity.Building;
import org.group.projects.simple.gis.api.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "gis", description = "Simple Gis API")
@RequestMapping(value = "api/gis")
public class SearchController {

    @Autowired
    private GeoService service;

    public SearchController() {
    }

    @CrossOrigin()
    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ApiOperation(value = "getAddressObjects")
    public @ResponseBody List<Building> getAddressObjects(@RequestParam String street) {

        return service.getBuildings(street, 5);
    }
}



