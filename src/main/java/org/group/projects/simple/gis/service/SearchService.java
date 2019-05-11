package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.SearchRequest;
import org.group.projects.simple.gis.model.SearchResult;
import org.group.projects.simple.gis.model.entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private GeoObjectService geoObjectService;

    public SearchResult getResult(SearchRequest request, int limit) {
        List<Building> buildings = geoObjectService.getBuildings(request.getContent(),
                limit < 5 ? 5 : limit * 2);
        SearchResult result = new SearchResult();
        result.setResults(buildings.stream()
                .map(eachBuilding -> {
                    return new SearchResult.Result(eachBuilding.getLon(),
                            eachBuilding.getLat(), eachBuilding.getAddress());
                })
                .collect(Collectors.toList()));
        result.setContent(request.getContent());

        return result;
    }

}
