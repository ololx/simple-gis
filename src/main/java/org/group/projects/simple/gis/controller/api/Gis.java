package org.group.projects.simple.gis.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.dao.BuildingManager;
import org.group.projects.simple.gis.model.entity.Building;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "gis", description = "Simple Gis API")
@RequestMapping(value = "api/gis")
public class Gis {

    @Autowired
    private BuildingManager manager;

    public Gis() {
    }

    @CrossOrigin()
    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ApiOperation(value = "getAddressObjects")
    public @ResponseBody List<Building> getTags(@RequestParam String street) {
        List<Building> buildings = manager.selectByFullAddress(street);

        return buildings.stream()
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
    }
}



