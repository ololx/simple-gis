package org.group.projects.simple.gis.online.client;

import feign.Param;
import org.group.projects.simple.gis.online.model.entity.Building;
import org.group.projects.simple.gis.online.model.transport.SearchRequest;
import org.group.projects.simple.gis.online.model.transport.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "simple-gis-api", url = "${simple_gis_api}")
public interface SimpleGisApiClient {

    @PostMapping(value = "/search/find")
    SearchResult findBuildings(@Param("request") SearchRequest request);

}
