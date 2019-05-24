package org.group.projects.simple.gis;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@Service
@RequiredArgsConstructor
public class Utils {

    @Autowired(required = true)
    private DataSource dataSource;

    public boolean isConnectionToBatabaseExist(){
        return dataSource != null ? true : false;
    }
}
