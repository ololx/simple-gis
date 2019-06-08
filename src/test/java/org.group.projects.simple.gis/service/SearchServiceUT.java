package org.group.projects.simple.gis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.Utils;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.model.transport.SearchRequest;
import org.group.projects.simple.gis.model.transport.SearchResult;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Category(UnitTest.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@Slf4j
@RequiredArgsConstructor
public class SearchServiceUT {

    @InjectMocks
    @Autowired(required = true)
    private SearchService searchService;

    @Autowired(required = true)
    protected Utils utils;

    @MockBean
    private GeoService geoService;

    @BeforeClass
    public static void beforeAllTest() {
        log.info("Starting test SearchService methods");
    }

    @AfterClass
    public static void afterAllTest() {
        log.info("Test SearchService methods has been completed");
    }

    @Before
    public void beforeEachTest() {
        log.info("Starting SearchService method test");

        when(geoService.getBuildings(any(String.class), any(Integer.class))).thenReturn(utils.getBuildingTemplates());
    }

    @After
    public void afterEachTest() {
        log.info("Completing SearchService method test");
    }

    @Test
    public void getResult() {
        SearchResult searchResult = utils.getSearchResultTemplate();
        log.info("Calling getResult method with request: \n content - {} \n count - {}", "", 0);
        SearchResult searchResultFromService = searchService.getResult(new SearchRequest(""), 0);
        Assert.assertNotNull("Failure! - search result is null", searchResultFromService);
        assertEquals("Failure! - search result are different", searchResult, searchResultFromService);

        log.info("Success! - a building was found \n {} \n {}", searchResult, searchResultFromService);
    }
}
