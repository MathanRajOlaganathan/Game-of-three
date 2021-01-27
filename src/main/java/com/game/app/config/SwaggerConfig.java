package com.game.app.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
public class SwaggerConfig {

    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.game.app"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("Mathan Raj Olaganathan","https://www.linkedin.com/in/mathanrajo",
                "omrfrance1990@gmail.com");

        StringVendorExtension listVendorExtension = new StringVendorExtension("Role", "Software Developer");
        ApiInfo apiInfo = new ApiInfo("Game-of-Three",
                " A game with two independent units – the players –\n" +
                        "communicating with each other using an API.",
                "1.0",
                "",
                (springfox.documentation.service.Contact) contact,
                "Game-of-Three - Github"
                ,"https://github.com/MathanRajOlaganathan/Game-of-three",
                Arrays.asList(listVendorExtension));
        return apiInfo;
    }
}
