package org.group.projects.simple.gis;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
