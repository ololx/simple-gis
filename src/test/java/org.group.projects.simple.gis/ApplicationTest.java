package org.group.projects.simple.gis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
/*@ContextConfiguration(classes = {
        ApplicationConfigTest.class
})*/
@ActiveProfiles("test")
@Slf4j
@RequiredArgsConstructor
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }
}
