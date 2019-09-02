package org.group.projects.simple.gis.api;

import lombok.NoArgsConstructor;
import org.group.projects.simple.gis.api.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Category(UnitTest.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext
@NoArgsConstructor
public class ApplicationUT {

    @Test
    public void contextLoads() {
    }
}
