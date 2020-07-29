package com.i2dsp.sa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author david
 * // 注意,Swagger版本过高(2.10.X等)就不是这个 @EnableSwagger2  注解了,然后还访问不了
 * <p>
 * 但是升级到3.0.0,依赖名变了, @EnableSwagger2 这个注解都不需要了
 * 访问地址:http://localhost:8083/swagger-ui/
 * 官网说明: https://github.com/springfox/springfox#spring-boot-applications-1
 */
@Configuration
//@EnableSwagger2
public class SwaggerTwoConfig {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.kevin.controller"))
//                .paths(PathSelectors.any())//paths(PathSelectors.regex("/.*"))
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("標題:Spring Boot中使用Swagger2建構RESTful APIs")
//                .description("相關說明")
//                .termsOfServiceUrl("https://www.pixnet.net/pcard/B0212066/")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .version("1.0.0")
//                .build();
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.i2dsp.sa.controller"))
                //paths(PathSelectors.regex("/.*"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot Combine Swagger")
                .description("Spring boot 整合Swagger详细信息^^^^^")
                .version("1.0.0")
                .contact(new Contact("Sanotsu个人博客", "http://www.swmlee.com", "davidsu@i2dsp.cn"))
                .license("The Apache 2.0  License")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }


}
