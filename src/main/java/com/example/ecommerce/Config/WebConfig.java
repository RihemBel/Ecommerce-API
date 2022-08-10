package com.example.ecommerce.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private final StorageProperties storageProps;

    public WebConfig(StorageProperties storageProps) {
        this.storageProps = storageProps;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/resources/**")
                .addResourceLocations("file:/home/med/Pictures/");*/

        String path = storageProps.getPath();
        // do your stuff here
        System.out.println(path);


        String realPath = path.substring(7,path.length());
        System.out.println(realPath);

       /* registry.addResourceHandler("/resources/**")
                .addResourceLocations("file:/home/med/Documents/workspace/angular+springboot/last/micro-service - gatwa+micro avec jwt - services/service-company/service-company/compaktorImages/");*/
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("file:"+realPath+"/");
        /* registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");*/

    }
}