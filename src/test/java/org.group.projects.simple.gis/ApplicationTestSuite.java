package org.group.projects.simple.gis;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.IntegrationTest;
import org.group.projects.simple.gis.categories.OnRealUnitTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.repository.BuildingRepositoryEmbededlTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(UnitTest.class)
@ExcludeCategory({
        IntegrationTest.class,
        OnRealUnitTest.class
})
@SuiteClasses({
        ApplicationTest.class,
        BuildingRepositoryEmbededlTest.class
})
@Slf4j
public class ApplicationTestSuite {

    public void main() {
        log.info("Starting application tests on emulated service: db, ...");
    }

}

