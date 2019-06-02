package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Category(OnRealTest.class)
@ActiveProfiles("test-on-real")
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryOnRealUT extends BuildingRepositoryUT {

    @Test
    public void findBuildingViaIndex() {
        Building templateBuilding = utils.getBuildingTemplate();
        buildingRepository.save(templateBuilding);
        assertNotEquals(0, buildingRepository.count());
        log.info("Saved one building entity into db \n {}", templateBuilding);

        Building resultBuilding = buildingRepository.findBuildingViaIndex("'Советский'").get(0);
        assertNotNull("Failure! - data weren't found", resultBuilding);
        assertEquals("Failure! - data are different", templateBuilding, resultBuilding);

        log.info("Success! - a building were found \n {} \n {}", templateBuilding, resultBuilding);
    }

}
