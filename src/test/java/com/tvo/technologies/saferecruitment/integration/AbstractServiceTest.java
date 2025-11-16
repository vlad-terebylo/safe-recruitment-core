package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.integration.config.TestConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import({TestConfig.class})
abstract public class AbstractServiceTest {
}
