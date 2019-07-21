package org.group.projects.simple.gis.online.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.Utils;
import org.group.projects.simple.gis.online.categories.UnitTest;
import org.group.projects.simple.gis.online.service.GeoService;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@Category(UnitTest.class)
@WebMvcTest(SearchController.class)
@DirtiesContext
@ActiveProfiles("test")
@Slf4j
@NoArgsConstructor
public class MainControllerUT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired(required = true)
    private Utils utils;

    @MockBean
    private GeoService geoService;

    @Test
    public void main() throws Exception {

       given(geoService.getBuildings(any(String.class), any(Integer.class)))
               .willReturn(utils.getBuildingTemplates());

        MockHttpServletResponse response = mockMvc.perform(get("/api/gis/getAddressObjects?street=Новосибирск")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals("Failure! - response is not OK", HttpStatus.OK.value(), response.getStatus());
        /*assertEquals("Failure! - response is not OK",
                utils.getBuildingTemplates().toString(),
                response.getContentAsString());*/

        log.info("Success! - a request was got \n {} \n {}", response.getStatus(), response.getContentAsString());
    }
}
