package org.group.projects.simple.gis.service;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GeoService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getBuildings(String address, int limit) {
        String keywords = GeoInformationService.getKeywords(address);
        log.info(keywords);

        List<Building> result =  buildingRepository.findBuildingViaIndex(
                keywords,
                new PageRequest(0, limit)).stream()
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