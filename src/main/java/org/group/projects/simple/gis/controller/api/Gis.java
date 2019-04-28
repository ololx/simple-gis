package org.group.projects.simple.gis.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.repository.BuildingManager;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.service.GeoInformationService;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<Building> result =  manager.selectByFullAddress(GeoInformationService.getBiGramms(street)).stream()
                .distinct()
                .sorted((currentBuilding, nextBuilding) -> Integer.compare(
                    GeoInformationService.getLevenstainDistance(street, currentBuilding.getStreet()),
                            GeoInformationService.getLevenstainDistance(street, nextBuilding.getStreet())
                )).limit(5)
                .collect(Collectors.toList());

        return result;
    }
}



