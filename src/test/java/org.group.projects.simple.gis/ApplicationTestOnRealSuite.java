package org.group.projects.simple.gis;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.IntegrationTest;
import org.group.projects.simple.gis.categories.OnRealUnitTest;
import org.group.projects.simple.gis.repository.BuildingRepositoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(OnRealUnitTest.class)
@ExcludeCategory(IntegrationTest.class)
@SuiteClasses({
        BuildingRepositoryTest.class
})
@Slf4j
public class ApplicationTestOnRealSuite {

    public void main() {
        log.info("Starting application tests on real service: db, ...");
    }
}

