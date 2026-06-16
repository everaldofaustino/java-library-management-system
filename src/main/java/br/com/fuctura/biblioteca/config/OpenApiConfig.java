package br.com.fuctura.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library management API",
                version = "1.0",
                description = "API REST pra gerenciamento de livros e categorias"
        )
)
public class OpenApiConfig {
}
