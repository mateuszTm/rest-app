package com.empik.restapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfiguration {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Bean("gitHubWebClient")
    public WebClient gitHubWebClient() {
        return WebClient.builder().baseUrl(gitHubApiUrl).build();
    }
}
