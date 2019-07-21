package org.group.projects.simple.gis.online.client;

import feign.Param;
import org.group.projects.simple.gis.online.model.entity.Building;
import org.group.projects.simple.gis.online.model.transport.SearchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "simple-gis-api", url = "${simple-gis-api.service}/${simple-gis-api.search}")
public interface SimpleGisApiClient {

    @PostMapping(value = "/find")
    Building[] findBuildings(@Param("searchRequest") SearchRequest searchRequest);

}
