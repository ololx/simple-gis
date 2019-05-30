package org.group.projects.simple.gis;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.EmbededlTest;
import org.group.projects.simple.gis.categories.IntegrationTest;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.repository.BuildingRepositoryEmbededUT;
import org.group.projects.simple.gis.repository.BuildingRepositoryUT;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(UnitTest.class)
@ExcludeCategory({
        IntegrationTest.class,
        OnRealTest.class
})
@SuiteClasses({
        ApplicationTest.class,
        BuildingRepositoryEmbededUT.class
})
@Slf4j
public class ApplicationTestSuite {

    public void main() {
        log.info("Starting application tests on emulated service: db, ...");
    }

}

