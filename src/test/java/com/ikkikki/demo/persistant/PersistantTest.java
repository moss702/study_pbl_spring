package com.ikkikki.demo.persistant;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootTest
@Slf4j
public class PersistantTest {
    @Autowired
    private HikariConfig hikariConfig;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testHikari() {
        log.info("hikari :: {} ", hikariConfig);
    }
    @Test
    public void testDataSource() {
        log.info("ds :: {}", dataSource);
    }

}
