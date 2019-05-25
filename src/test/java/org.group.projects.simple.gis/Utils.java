package org.group.projects.simple.gis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

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
