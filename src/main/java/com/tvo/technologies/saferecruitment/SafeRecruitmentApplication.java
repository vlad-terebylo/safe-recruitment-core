package com.tvo.technologies.saferecruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("com.tvo.technologies.saferecruitment.properties")
public class SafeRecruitmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafeRecruitmentApplication.class, args);
    }
}
