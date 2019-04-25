package org.group.projects.simple.gis.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.group.projects.simple.gis.dao.AddressObjectManager;
import org.group.projects.simple.gis.model.entity.FiasAddress;
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
    @RequestMapping(value = "/getAddressByName", method = RequestMethod.POST, produces = "application/data")
    @ApiOperation(value = "getAddressByName")
    public ResponseEntity<String> addQuestion(@RequestParam("content") String content) {
        AddressObjectManager mDao = new AddressObjectManager();
        List<FiasAddress> mAddressObjects = mDao.selectByFormalName(content);
        String mResult = String.format("{\"data\": %s}", mAddressObjects.stream()
                .map(ea -> ea.toString())
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(mResult, HttpStatus.OK);
    }

    @CrossOrigin()
    @RequestMapping(value = "/getAddressObjects", method = RequestMethod.GET)
    @ApiOperation(value = "getAddressObjects")
    public @ResponseBody List<FiasAddress> getTags(@RequestParam String tagName) {
        AddressObjectManager mDao = new AddressObjectManager();
        List<FiasAddress> mAddressObjects = mDao.selectByFormalName(tagName);
        return mAddressObjects.stream()
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
    }
}



