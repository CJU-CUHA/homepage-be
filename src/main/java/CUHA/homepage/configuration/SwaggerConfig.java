package CUHA.homepage.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .version("1.0")//버전
                .title("Prototype Capstone")
                .description("This is a capstone.");
        return new OpenAPI()
                .info(info);
    }

}
