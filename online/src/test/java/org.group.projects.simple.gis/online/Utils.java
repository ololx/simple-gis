package org.group.projects.simple.gis.online;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.model.entity.Building;
import org.group.projects.simple.gis.online.model.transport.BuildingDetail;
import org.group.projects.simple.gis.online.model.transport.SearchResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Utils {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Value("classpath:building-detail-template.json")
    private Resource buildingDetailTemplate;

    @Value("classpath:building-template.json")
    private Resource buildingTemplate;

    public Building getBuildingTemplate() {
        Building templateBuilding = null;

        try {
            templateBuilding = objectMapper.readValue(buildingTemplate.getFile(),
                    Building.class);
        } catch (IOException e) {
            log.debug("Failure! - exception: " + e.toString());
        }

        return templateBuilding;
    }

    public List<Building> getBuildingTemplates() {
        return new ArrayList<Building>(){{
            add(getBuildingTemplate());
        }};
    }

    public BuildingDetail getBuildingDetailTemplate() {
        Building templateBuilding = getBuildingTemplate();
        BuildingDetail templateBuildingDetail = modelMapper.map(templateBuilding, BuildingDetail.class);

        return templateBuildingDetail;
    }

    public SearchResult getSearchResultTemplate() {
        Building buildingTemplate = getBuildingTemplate();

        return new SearchResult(
                new ArrayList<SearchResult.Result>(){{
                    add(new SearchResult.Result(
                            buildingTemplate.getLon(),
                            buildingTemplate.getLat(),
                            buildingTemplate.getAddress())
                    );
                }},
                ""
        );
    }

}
