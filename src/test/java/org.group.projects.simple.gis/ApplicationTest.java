package org.group.projects.simple.gis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@SpringBootTest
@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ActiveProfiles("test")
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }
}
