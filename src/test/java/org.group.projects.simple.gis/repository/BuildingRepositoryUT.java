package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.IntegrationTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@Transactional
@Slf4j
@NoArgsConstructor
public abstract class BuildingRepositoryUT {

    @Autowired(required = true)
    protected BuildingRepository buildingRepository;

    @Test
    public void findAllTest() {
        log.info("[BuildingRepository]: start search building against - '*ново*'");

        List<Building> result = new ArrayList<Building>() {
            {
                Iterator<Building> buildingIterator = buildingRepository.findAll().iterator();
                while(buildingIterator.hasNext()) {
                    add(buildingIterator.next());
                }
            }
        };

        log.info(String.format("[BuildingRepository]: search result = %s",
                (result.parallelStream()
                        .map(eachBuilding -> eachBuilding.toString())
                        .collect(Collectors.joining(", ")))));
    }

}
