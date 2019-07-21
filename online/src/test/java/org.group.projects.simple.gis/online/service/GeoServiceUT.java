package org.group.projects.simple.gis.online.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.Utils;
import org.group.projects.simple.gis.online.categories.UnitTest;
import org.group.projects.simple.gis.online.model.entity.Building;
import org.group.projects.simple.gis.online.repository.BuildingRepository;
import org.group.projects.simple.gis.online.service.GeoService;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Category(UnitTest.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@Slf4j
@RequiredArgsConstructor
public class GeoServiceUT {

    @InjectMocks
    @Autowired(required = true)
    private GeoService geoService;

    @Autowired(required = true)
    private Utils utils;

    @MockBean
    private BuildingRepository buildingRepository;

    @BeforeClass
    public static void beforeAllTest() {
        log.info("Starting test GeohService methods");
    }

    @AfterClass
    public static void afterAllTest() {
        log.info("Test GeohService methods has been completed");
    }

    @Before
    public void beforeEachTest() {
        log.info("Starting GeohService method test");

        when(buildingRepository.findBuildingViaIndex(any(String.class), any(Pageable.class)))
                .thenReturn(utils.getBuildingTemplates());
        when(buildingRepository.findBuildingById(any(Integer.class))).thenReturn(utils.getBuildingTemplate());
    }

    @After
    public void afterEachTest() {
        log.info("Completing GeohService method test");
    }

    @Test
    public void getResult() {
        List<Building> buildingsTemplates = utils.getBuildingTemplates();

        log.info("Calling getBuildings method with request: \n content - {} \n count - {}", "", 1);
        List<Building> buildingsFromService = geoService.getBuildings("", 1);
        assertNotNull("Failure! - buildings list is null", buildingsFromService);
        assertNotEquals("Failure! - buildings list is empty", buildingsFromService);
        assertEquals("Failure! - buildings lists are different", buildingsTemplates, buildingsFromService);

        log.info("Success! - a building was found \n {} \n {}", buildingsTemplates, buildingsFromService);
    }

    @Test
    public void getBuilding() {
        Building buildingTemplate = utils.getBuildingTemplate();

        log.info("Calling getBuilding method with request - ID = {}", "", 0);
        Building buildingsFromService = geoService.getBuilding( 0);
        assertNotNull("Failure! - building is null", buildingsFromService);
        assertEquals("Failure! - buildings are different", buildingTemplate, buildingsFromService);

        log.info("Success! - a building was found \n {} \n {}", buildingTemplate, buildingsFromService);
    }
}
