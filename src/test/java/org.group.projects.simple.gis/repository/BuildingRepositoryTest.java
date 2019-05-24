package org.group.projects.simple.gis.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.Utils;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@SpringBootTest
@Slf4j
//@RequiredArgsConstructor
@ActiveProfiles("test")
@Transactional
public class BuildingRepositoryTest  {

    @Autowired(required = true)
    private static BuildingRepository buildingRepository;

    @Autowired(required = true)
    private static Utils utils;

    @Autowired
    public BuildingRepositoryTest() {
    }

    @BeforeClass
    public static void beforeAllTest() {
    }

    @Before
    public void beforeEachTest() {
        log.info(buildingRepository.toString());
        Assume.assumeTrue(buildingRepository != null);
        log.info(String.valueOf(utils.isConnectionToBatabaseExist()));
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void findAllTest() {

        log.info("[BuildingRepository]: start search building against - '*ново*'");

        /*List<Building> result = new ArrayList<Building>() {
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
                        .collect(Collectors.joining(", ")))));*/
    }
}
