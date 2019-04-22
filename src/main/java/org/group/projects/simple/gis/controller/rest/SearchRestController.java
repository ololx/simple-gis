package org.group.projects.simple.gis.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.dao.AddressObjectManager;
import org.group.projects.simple.gis.model.entity.AddressObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "SearchRestController", description = "Search address objects by params")
@RequestMapping(value = "rest/search-rest-controller")
public class SearchRestController {

    public SearchRestController() {
    }
    
    @CrossOrigin()
    @RequestMapping(value = "/getAddressByName", method = RequestMethod.POST, produces = "application/data")
    @ApiOperation(value = "getAddressByName")
    public ResponseEntity<String> addQuestion(@RequestParam("content") String content) {
        AddressObjectManager mDao = new AddressObjectManager();
        List<AddressObject> mAddressObjects = mDao.selectByFormalName(content);
        return new ResponseEntity<>(mAddressObjects.stream()
                .map(ea -> ea.toString())
                .collect(Collectors.joining(", ")), HttpStatus.OK);
    }
}



