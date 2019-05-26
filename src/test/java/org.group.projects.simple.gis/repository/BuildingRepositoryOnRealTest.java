package org.group.projects.simple.gis.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.Application;
import org.group.projects.simple.gis.Utils;
import org.group.projects.simple.gis.categories.OnRealUnitTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@Category(OnRealUnitTest.class)
@SpringBootTest
@ActiveProfiles("test-on-real")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BuildingRepositoryOnRealTest {

    @Autowired(required = true)
    private BuildingRepository buildingRepository;

    @Test
    public void findAllTest() {
        log.info("[BuildingRepository]: start search building against - '*ново*'");

        List<Building> result = new ArrayList<Building>() {
            {
                Iterator<Building> buildingIterator = buildingRepository.findAll().iterator();
                while(buildingIterator.hasNext()) {
                    add(buildingIterator.next());
                }
            }
        };

        log.info(String.format("[BuildingRepository]: search result = %s",
                (result.parallelStream()
                        .map(eachBuilding -> eachBuilding.toString())
                        .collect(Collectors.joining(", ")))));
    }

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
