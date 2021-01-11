package org.arrnaux.timetracker.store.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

@EnableJpaRepositories(basePackages = ["org.arrnaux.timetracker.store"])
@Configuration
class DataStoreConfiguration {
    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver")
        dataSource.username = "ltw-application"
        dataSource.password = "ltw-password"
        dataSource.url = "jdbc:mariadb://localhost:3306/timetracker"

        return dataSource

    }

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
        jpaVendorAdapter: JpaVendorAdapter
    ): LocalContainerEntityManagerFactoryBean {
        val bean = LocalContainerEntityManagerFactoryBean()
        bean.dataSource = dataSource
        bean.jpaVendorAdapter = jpaVendorAdapter
        bean.setPackagesToScan("org.arrnaux.timetracker")
        return bean
    }
}