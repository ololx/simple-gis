package org.group.projects.simple.gis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.dto.BuildingDetail;
import org.group.projects.simple.gis.model.entity.Building;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public BuildingDetail getBuildingDetailTemplate() {
        Building templateBuilding = getBuildingTemplate();
        BuildingDetail templateBuildingDetail = modelMapper.map(templateBuilding, BuildingDetail.class);

        return templateBuildingDetail;
    }

}
