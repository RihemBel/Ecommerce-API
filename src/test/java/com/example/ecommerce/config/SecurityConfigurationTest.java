package com.example.ecommerce.config;

import com.example.ecommerce.Config.SecurityConfiguration;
import com.example.ecommerce.Security.Jwt.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.MockitoAnnotations.initMocks;

class SecurityConfigurationTest {

    @Mock
    private TokenProvider mockTokenProvider;

    private SecurityConfiguration securityConfigurationUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        securityConfigurationUnderTest = new SecurityConfiguration(mockTokenProvider);
    }

    @Test
    void testConfigure1() {
        // Setup
        final WebSecurity web = new WebSecurity(null);

        // Run the test
        securityConfigurationUnderTest.configure(web);

        // Verify the results
    }

    @Test
    void testConfigure2() throws Exception {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null), new HashMap<>());

        // Run the test
        securityConfigurationUnderTest.configure(http);

        // Verify the results
    }

    @Test
    void testConfigure2_ThrowsException() {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null), new HashMap<>());

        // Run the test
        assertThatThrownBy(() -> securityConfigurationUnderTest.configure(http)).isInstanceOf(Exception.class);
    }
}
