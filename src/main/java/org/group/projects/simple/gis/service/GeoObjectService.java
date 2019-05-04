package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoObjectService {

    /*@Autowired
    private BuildingManager manager;*/

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getBuildings(String street, int limit) {

        List<Building> result =  buildingRepository.findBuildingViaIndex/*manager.selectByFullAddress*/(GeoInformationService.getBiGramms(street)).stream()
                .distinct()
                .sorted((currentBuilding, nextBuilding) -> Integer.compare(
                        GeoInformationService.getLevenstainDistance(street, currentBuilding.getStreet()),
                        GeoInformationService.getLevenstainDistance(street, nextBuilding.getStreet())
                )).limit(limit)
                .collect(Collectors.toList());

        return result;
    }

}
