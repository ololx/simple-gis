package org.group.projects.simple.gis.api;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.categories.EmbededTest;
import org.group.projects.simple.gis.api.categories.IntegrationTest;
import org.group.projects.simple.gis.api.categories.UnitTest;
import org.group.projects.simple.gis.api.repository.BuildingRepositoryOnRealUT;
import org.group.projects.simple.gis.api.service.GeoServiceUT;
import org.group.projects.simple.gis.api.service.SearchServiceUT;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(UnitTest.class)
@ExcludeCategory({
        IntegrationTest.class,
        EmbededTest.class
})
@SuiteClasses({
        ApplicationUT.class,
        BuildingRepositoryOnRealUT.class,
        SearchServiceUT.class,
        GeoServiceUT.class
})
@Slf4j
public class ApplicationOnRealUTSuite {

    public void main() {
        log.info("Starting application tests on real service: db, ...");
    }
}

