package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.Utils;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@Transactional
@Slf4j
@NoArgsConstructor
public abstract class BuildingRepositoryUT {

    @Autowired(required = true)
    protected BuildingRepository buildingRepository;

    @Autowired(required = true)
    protected Utils utils;

    @BeforeClass
    public static void beforeAllTest() {
        log.info("Starting test BuildingRepository methods");
    }

    @AfterClass
    public static void afterAllTest() {
        log.info("Test BuildingRepository methods has been completed");
    }

    private int id;

    @Before
    public void beforeEachTest() {
        log.info(String.format("Completing BuildingRepository method test with data count = %s",
                clear()));

    }

    @After
    public void afterEachTest() {
        log.info(String.format("Completing BuildingRepository method test with data count = %s",
                clear()));
    }

    @Test
    public void findById() {
        log.info("Starting test for BuildingRepository method - findById");
        assertEquals(0, buildingRepository.count());

        Building building = utils.getBuilding();
        buildingRepository.save(building);
        log.info(String.valueOf(buildingRepository.count()));

        log.info("Starting test for BuildingRepository method - findById");
        assertNotEquals(0, buildingRepository.count());

        log.info(String.format("Getting building by id = %s", building.getId()));
        assertNotNull("Failure! - ", buildingRepository.findBuildingById(building.getId()));

        log.info("The test for BuildingRepository method has been completed");
    }

    protected long clear() {
        log.info("Starting delete all data from the test database");

        buildingRepository.deleteAll();
        long buildingCount = buildingRepository.count();
        assertEquals("Failure! - some data haven't been deleted", 0, buildingCount);

        log.info("Success! - all module instances have been deleted");

        return buildingCount;
    }

}
