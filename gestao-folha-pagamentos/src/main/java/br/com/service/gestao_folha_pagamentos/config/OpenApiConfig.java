package br.com.service.gestao_folha_pagamentos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto MVC Folha de pagamentos de fucionários.")
                        .version("v1")
                        .description("Monolito usando a arquitetura em camadas mvc, de uma gestão de folha de funcionários.")
                        .contact(new Contact()
                                .name("Rudge Santos")
                                .url("https://www.linkedin.com/in/rudge-santos/")
                        )
                        .termsOfService("RudgeS")
                        .license(new License()
                                .name("Apache 2.0"))
                );
    }

}
