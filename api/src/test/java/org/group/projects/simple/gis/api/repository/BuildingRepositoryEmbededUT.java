package org.group.projects.simple.gis.api.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.categories.EmbededTest;
import org.junit.experimental.categories.Category;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.test.context.ActiveProfiles;

@Category(EmbededTest.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryEmbededUT extends BuildingRepositoryUT {
    //Nothing additional
}
