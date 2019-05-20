package org.group.projects.simple.gis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@DirtiesContext
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ApplicationConfigTest.class})
public class ApplicationTest {

    @DisplayName("Context load test")
    @Test
    public void contextLoads() {
    }
}
