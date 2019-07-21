package org.group.projects.simple.gis.online.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.client.SimpleGisApiClient;
import org.group.projects.simple.gis.online.model.entity.Building;
import org.group.projects.simple.gis.online.model.transport.SearchRequest;
import org.group.projects.simple.gis.online.model.transport.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class SearchService {

    @Autowired
    private final SimpleGisApiClient simpleGisApiClient;

    public SearchResult getResult(SearchRequest request, int limit) {
        log.warn("Send request to simple-gis/api controller {}", request);
        SearchResult searchResult = simpleGisApiClient.findBuildings(request);
        log.warn("Response {}", searchResult.toString());

        return new SearchResult(searchResult.getResults()
                .stream()
                .map(eachBuilding ->  new SearchResult.Result(
                        eachBuilding.getLon(),
                        eachBuilding.getLat(),
                        eachBuilding.getAddress()))
                .collect(Collectors.toList()),
                request.getContent());
    }
}
