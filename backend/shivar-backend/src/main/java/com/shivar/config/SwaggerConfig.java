package com.shivar.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI shivarOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("SHIVAR API")
                        .description("🌾 SHIVAR - तुमचं डिजिटल शिवार\n\nDigital Agriculture Platform Backend APIs")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("SHIVAR Development Team")
                                .email("support@shivar.com"))
                        .license(new License()
                                .name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("SHIVAR Documentation"));
    }
}