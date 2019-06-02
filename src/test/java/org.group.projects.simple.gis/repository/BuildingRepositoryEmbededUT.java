package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.EmbededlTest;
import org.junit.experimental.categories.Category;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Category(EmbededlTest.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
@Transactional
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryEmbededUT extends BuildingRepositoryUT {
    //Nothing additional
}
