package org.group.projects.simple.gis.online.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.Utils;
import org.group.projects.simple.gis.online.model.entity.Building;
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
        log.info("Started test of the BuildingRepository methods");
    }

    @AfterClass
    public static void afterAllTest() {
        log.info("Completed test of the BuildingRepository methods");
    }

    @Before
    public void beforeEachTest() {
        clear();
        log.info("Success! - a test starts");
    }

    @After
    public void afterEachTest() {
        clear();
        log.info("Success! - a test has been completed");
    }

    @Test
    public void findById() {
        Building templateBuilding = utils.getBuildingTemplate();
        buildingRepository.save(templateBuilding);
        assertNotEquals(0, buildingRepository.count());
        log.info("Saved one building entity into db \n {}", templateBuilding);

        Building resultBuilding = buildingRepository.findBuildingById(templateBuilding.getId());
        assertNotNull("Failure! - data weren't found", resultBuilding);
        assertEquals("Failure! - data are different", templateBuilding, resultBuilding);

        log.info("Success! - a building was found \n {} \n {}", templateBuilding, resultBuilding);
    }

    protected void clear() {
        log.info("Started the test database preparation - the clearing of data");

        buildingRepository.deleteAll();
        assertEquals("Failure! - some data haven't been deleted", 0, buildingRepository.count());

        log.info("Success! - all data have been deleted");
    }

}
