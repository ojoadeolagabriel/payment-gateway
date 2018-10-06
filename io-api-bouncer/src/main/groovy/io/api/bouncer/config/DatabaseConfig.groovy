package io.api.bouncer.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

import javax.sql.DataSource

@Component
@ConfigurationProperties(prefix = "apps.datasource")
class DatabaseConfig extends HikariConfig{
	@Bean
	DataSource dataSource(){
		return new HikariDataSource(this)
	}
}
