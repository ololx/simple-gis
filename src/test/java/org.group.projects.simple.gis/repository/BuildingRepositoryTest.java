package org.group.projects.simple.gis.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@RequiredArgsConstructor
public class BuildingRepositoryTest  {

    @Autowired(required = true)
    private BuildingRepository buildingRepository;

    @Test
    public void findBuildingById() {
        log.info("[BuildingRepository]: stast search building by id = '12'");
        Building result = buildingRepository.findBuildingById(String.valueOf(12));
        log.info(String.format("[BuildingRepository]: search result = %s", result));
    }
}
