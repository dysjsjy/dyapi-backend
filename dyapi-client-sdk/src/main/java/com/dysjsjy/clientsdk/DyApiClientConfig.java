package com.dysjsjy.clientsdk;


import com.dysjsjy.clientsdk.cilent.DyApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("dyapi.client")
@Configuration
public class DyApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public DyApiClient DyApiClient() {
        return new DyApiClient(accessKey, secretKey);
    }
}
