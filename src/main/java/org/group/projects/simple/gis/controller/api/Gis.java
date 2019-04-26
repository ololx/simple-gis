package org.group.projects.simple.gis.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.dao.fias.AddressObjectManager;
import org.group.projects.simple.gis.dao.gis2.BuildingManager;
import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.model.entity.gis2.Building;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "gis", description = "Simple Gis API")
@RequestMapping(value = "api/gis")
public class Gis {

    public Gis() {
    }

    @CrossOrigin()
    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ApiOperation(value = "getAddressObjects")
    public @ResponseBody List<Building> getTags(@RequestParam String street) {
        BuildingManager mDao = new BuildingManager();
        List<Building> mAddressObjects = mDao.selectByFormalName(street);
        return mAddressObjects.stream()
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
    }
}



