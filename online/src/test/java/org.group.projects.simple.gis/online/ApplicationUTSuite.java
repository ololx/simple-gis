package org.group.projects.simple.gis.online;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.online.categories.IntegrationTest;
import org.group.projects.simple.gis.online.categories.OnRealTest;
import org.group.projects.simple.gis.online.categories.UnitTest;
import org.group.projects.simple.gis.online.repository.BuildingRepositoryEmbededUT;
import org.group.projects.simple.gis.online.service.GeoServiceUT;
import org.group.projects.simple.gis.online.service.SearchServiceUT;
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

