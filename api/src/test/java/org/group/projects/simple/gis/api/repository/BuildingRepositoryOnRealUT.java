package org.group.projects.simple.gis.api.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.categories.OnRealTest;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.ActiveProfiles;

@Category(OnRealTest.class)
@ActiveProfiles("test-on-real")
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryOnRealUT extends BuildingRepositoryUT {
    //Nothing additional
}
