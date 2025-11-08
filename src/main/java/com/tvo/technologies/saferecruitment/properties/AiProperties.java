package com.tvo.technologies.saferecruitment.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ai")
public record AiProperties(String geminiKey, String openaiKey) {
}