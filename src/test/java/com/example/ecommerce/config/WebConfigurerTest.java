//package com.example.ecommerce.config;
//
//import com.example.ecommerce.Config.WebConfigurer;
//import com.example.ecommerce.Security.Jwt.MyWayProperties;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.core.env.Environment;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//class WebConfigurerTest {
//
//    @Mock
//    private Environment mockEnv;
//    @Mock
//    private MyWayProperties mockWayProperties;
//
//    private WebConfigurer webConfigurerUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        initMocks(this);
//        webConfigurerUnderTest = new WebConfigurer(mockEnv, mockWayProperties);
//    }
//
//    @Test
//    void testOnStartup() throws Exception {
//        // Setup
//        final ServletContext servletContext = null;
//        when(mockEnv.getActiveProfiles()).thenReturn(new String[]{"value"});
//
//        // Run the test
//        webConfigurerUnderTest.onStartup(servletContext);
//
//        // Verify the results
//    }
//
//    @Test
//    void testOnStartup_ThrowsServletException() {
//        // Setup
//        final ServletContext servletContext = null;
//        when(mockEnv.getActiveProfiles()).thenReturn(new String[]{"value"});
//
//        // Run the test
//        assertThatThrownBy(() -> webConfigurerUnderTest.onStartup(servletContext)).isInstanceOf(ServletException.class);
//    }
//
//    @Test
//    void testCorsFilter() {
//        // Setup
//
//        // Run the test
//        final CorsFilter result = webConfigurerUnderTest.corsFilter();
//
//        // Verify the results
//    }
//
//}
