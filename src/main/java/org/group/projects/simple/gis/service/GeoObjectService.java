package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoObjectService {

    @Autowired
    private BuildingManager manager;

    public List<Building> getBuildings(String street, int limit) {

        List<Building> result =  manager.selectByFullAddress(GeoInformationService.getBiGramms(street)).stream()
                .distinct()
                .sorted((currentBuilding, nextBuilding) -> Integer.compare(
                        GeoInformationService.getLevenstainDistance(street, currentBuilding.getStreet()),
                        GeoInformationService.getLevenstainDistance(street, nextBuilding.getStreet())
                )).limit(limit)
                .collect(Collectors.toList());

        return result;
    }
}
