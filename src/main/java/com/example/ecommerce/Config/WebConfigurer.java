//package com.example.ecommerce.Config;
//
//import com.example.ecommerce.Security.Jwt.MyWayProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.Profiles;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import java.util.Arrays;
//import java.util.Collections;
//
///**
// * Configuration of web application with Servlet 3.0 APIs.
// */
//@Configuration
//public class WebConfigurer implements ServletContextInitializer {
//
//    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);
//
//    private final Environment env;
//
//    private final MyWayProperties myWayProperties;
//
//    public WebConfigurer(Environment env, MyWayProperties myWayProperties) {
//        this.env = env;
//        this.myWayProperties = myWayProperties;
//    }
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        if (env.getActiveProfiles().length != 0) {
//            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
//        }
//
////        if (env.acceptsProfiles(Profiles.of(MyWayProperties.SPRING_PROFILE_DEVELOPMENT))) {
////            initH2Console(servletContext);
////        }
////        log.info("Web application fully configured");
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = myWayProperties.getCors();
////        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
////            log.debug("Registering CORS filter");
////            source.registerCorsConfiguration("/api/**", config);
////            source.registerCorsConfiguration("/management/**", config);
////            source.registerCorsConfiguration("/v2/api-docs", config);
////        }
////        return new CorsFilter(source);
////    }
//    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    final CorsConfiguration config = new CorsConfiguration();
//    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Provide list of origins if you want multiple origins
//    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//    config.setAllowCredentials(true);
//    source.registerCorsConfiguration("/**", config);
//    return new CorsFilter(source);
//}
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = jHipsterProperties.getCors();
////        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
////            log.debug("Registering CORS filter");
////            source.registerCorsConfiguration("/api/**", config);
////            source.registerCorsConfiguration("/management/**", config);
////            source.registerCorsConfiguration("/v2/api-docs", config);
////        }
////        return new CorsFilter(source);
////    }
//
//    /*@Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(false);
//        config.setAllowedOrigins(Collections.singletonList("*"));
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }*/
//    /**
//     * Initializes H2 console.
//     */
////    private void initH2Console(ServletContext servletContext) {
////        log.debug("Initialize H2 console");
////        H2ConfigurationHelper.initH2Console(servletContext);
////    }
//
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        return new JavaMailSenderImpl();
//    }
//}
//
//
