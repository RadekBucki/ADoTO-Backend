package pl.ioad.adoto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@OpenAPIDefinition
public class AdotoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdotoBackendApplication.class, args);
    }

}
