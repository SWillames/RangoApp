package br.com.swsoftware.rangoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(
        new Info()
            .title("Rango API")
            .description("API para gerenciamento de usuários do sistema RangoApp")
            .version("v1")
            .contact(new Contact()
                .name("SW Software")
                .email("sergiowoc@gmail.com"))
    );
  }
}
