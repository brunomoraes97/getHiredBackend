package dev.gethired.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.sqlite.SQLiteDataSource;
import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class SQLiteDialectConfig {

    @Bean
    public DataSource dataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:get-hired-db.sqlite");
        return dataSource;
    }
}