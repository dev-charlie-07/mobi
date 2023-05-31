package com.mobiversa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource({ "classpath*:springxml/persistance-beans/datasource.xml",
        "classpath*:springxml/persistance-beans/hibernate.xml",
        "classpath*:springxml/services-beans/services-beans.xml",
        "classpath*:springxml/spring-security/spring-security.xml",
        "classpath*:springxml/aop-beans/aop-beans.xml" })
@EnableTransactionManagement
public class AppConfig {
    // This class can be empty or contain additional Java-based configuration
}
