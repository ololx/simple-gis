package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.EmbededlTest;
import org.group.projects.simple.gis.categories.IntegrationTest;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Category(EmbededlTest.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryEmbededUT extends BuildingRepositoryUT {
}
