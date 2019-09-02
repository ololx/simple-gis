package org.group.projects.simple.gis.api;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.categories.IntegrationTest;
import org.group.projects.simple.gis.api.categories.OnRealTest;
import org.group.projects.simple.gis.api.categories.UnitTest;
import org.group.projects.simple.gis.api.repository.BuildingRepositoryEmbededUT;
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
        OnRealTest.class
})
@SuiteClasses({
        ApplicationUT.class,
        BuildingRepositoryEmbededUT.class,
        SearchServiceUT.class,
        GeoServiceUT.class
})
@Slf4j
public class ApplicationUTSuite {

    public void main() {
        log.info("Started application tests on emulated service: db, ...");
    }

}

