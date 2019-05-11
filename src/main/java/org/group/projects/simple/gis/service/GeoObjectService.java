package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoObjectService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getBuildings(String address, int limit) {
        List<Building> result =  buildingRepository.findBuildingViaIndex(
                GeoInformationService.getKeywords(address),
                new PageRequest(0, limit < 5 ? 5 : limit * 2)).stream()
                .distinct()
                .sorted((currentBuilding, nextBuilding) -> Integer.compare(
                        GeoInformationService.keyWordsComare(address, currentBuilding.getAddress()),
                        GeoInformationService.keyWordsComare(address, nextBuilding.getAddress())
                )).limit(limit)
                .collect(Collectors.toList());

        return result;
    }

    public Building getBuilding(int id) {
        Building result = buildingRepository.findBuildingById(id);

        return result;
    }

}
