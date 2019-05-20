package org.group.projects.simple.gis.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@RequiredArgsConstructor
public class BuildingRepositoryTest  {

    @Autowired(required = true)
    private BuildingRepository buildingRepository;

    @Test
    public void findBuildingViaIndex() {
        log.info("[BuildingRepository]: start search building against - '*ново*'");
        List<Building> result = buildingRepository.findBuildingViaIndex("*ново*",
                new PageRequest(0, 3));
        log.info(String.format("[BuildingRepository]: search result = %s",
                (result.parallelStream()
                        .map(eachBuilding -> eachBuilding.toString())
                        .collect(Collectors.joining(", ")))));
    }
}
