package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoObjectService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getBuildings(String street, int limit) {

        System.out.println(GeoInformationService.getKeywords(street));

        List<Building> result =  buildingRepository.findBuildingViaIndex(
                GeoInformationService.getKeywords(street),
                new PageRequest(0, limit < 10 ? 10 : limit * 2)).stream()
                .distinct()
                .sorted((currentBuilding, nextBuilding) -> Integer.compare(
                        GeoInformationService.keyWordsComare(street, currentBuilding.getAddress()),
                        GeoInformationService.keyWordsComare(street, nextBuilding.getAddress())
                )).limit(limit)
                .collect(Collectors.toList());

        return result;
    }

    public Building getBuilding(int id) {
        Building result = buildingRepository.findBuildingById(id);

        return result;
    }

}
