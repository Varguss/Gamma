package ru.gamma_station.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.gamma_station.dao.*;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    @Bean(name = "gammaDataSource", destroyMethod = "close")
    public BasicDataSource getGammaDatabaseSource() throws IOException {
        BasicDataSource databaseDataSource = new BasicDataSource();

        Properties properties = new Properties();

        ClassPathResource propertiesFileResource = new ClassPathResource("database.properties");
        try (InputStream stream = propertiesFileResource.getInputStream()) {
            properties.load(stream);
        }

        databaseDataSource.setDriverClassName(properties.getProperty("gamma.driverClassName"));
        databaseDataSource.setUrl(properties.getProperty("gamma.url"));
        databaseDataSource.setUsername(properties.getProperty("gamma.username"));
        databaseDataSource.setPassword(properties.getProperty("gamma.password"));

        return databaseDataSource;
    }

    @Bean(name = "erisDataSource", destroyMethod = "close")
    public BasicDataSource getErisDatabaseSource() throws IOException {
        BasicDataSource databaseDataSource = new BasicDataSource();

        Properties properties = new Properties();

        ClassPathResource propertiesFileResource = new ClassPathResource("database.properties");
        try (InputStream stream = propertiesFileResource.getInputStream()) {
            properties.load(stream);
        }

        databaseDataSource.setDriverClassName(properties.getProperty("eris.driverClassName"));
        databaseDataSource.setUrl(properties.getProperty("eris.url"));
        databaseDataSource.setUsername(properties.getProperty("eris.username"));
        databaseDataSource.setPassword(properties.getProperty("eris.password"));

        return databaseDataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getGammaDatabaseSource());
        emf.setPackagesToScan("ru.gamma_station.domain", "ru.gamma_station.domain.website");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "utf8");
        properties.setProperty("hibernate.connection.charSet", "utf8");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(properties);

        return emf;
    }

    @Bean("gammaBanDAO")
    public BanDAO getGammaBanDAO() throws IOException {
        return new DatabaseGammaBanDAO(getGammaDatabaseSource());
    }

    @Bean("erisBanDAO")
    public BanDAO getErisBanDAO() throws IOException {
        return new DatabaseErisBanDAO(getErisDatabaseSource());
    }
//
//    @Bean("ruleEntityDAO")
//    public RuleDAO getRuleDAO() {
//        return new DatabaseRuleDAO();
//    }
//
//    @Bean("adminDAO")
//    public AdminDAO getAdminDAO() {
//        return new DatabaseAdminDAO();
//    }
//
//    @Bean("crewMemberDAO")
//    public CrewMemberDAO getCrewMemberDAO() {
//        return new DatabaseCrewMemberDAO();
//    }
//
//    @Bean("visitorEntityDAO")
//    public VisitorDAO getVisitorEntityDAO() throws IOException {
//        return new DatabaseVisitorDAO(getGammaDatabaseSource());
//    }
}