package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Server localServer = new Server()
                .url("https://localhost:8080")
                .description("Local Development Server");

        Server deployedServer = new Server()
                .url("https://9035.pro604cr.amypo.ai/")
                .description("Deployed Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Grid Load Shedding Controller API")
                        .description("REST APIs for zone management, demand readings, supply forecasting, load shedding, and restoration")
                        .version("1.0.0")
                )
                .servers(List.of(localServer, deployedServer));
    }
}
