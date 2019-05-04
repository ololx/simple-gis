package org.group.projects.simple.gis.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "gis", description = "Simple Gis API")
@RequestMapping(value = "api/gis")
public class Gis {

    @Autowired
    private GeoObjectService service;

    public Gis() {
    }

    @CrossOrigin()
    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ApiOperation(value = "getAddressObjects")
    public @ResponseBody List<Building> getTags(@RequestParam String street) {
        List<Building> result = service.getBuildings(street, 5);

        return result;
    }
}



