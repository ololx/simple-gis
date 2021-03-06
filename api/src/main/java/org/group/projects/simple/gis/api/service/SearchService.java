package org.group.projects.simple.gis.api.service;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.model.transport.SearchRequest;
import org.group.projects.simple.gis.api.model.transport.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchService {

    @Autowired
    private GeoService geoService;

    public SearchResult getResult(SearchRequest request, int limit) {
        return new SearchResult(geoService.getBuildings(request.getContent(), limit));
    }
}
