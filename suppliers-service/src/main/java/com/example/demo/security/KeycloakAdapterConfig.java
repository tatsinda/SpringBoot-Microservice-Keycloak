package com.example.demo.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//classe permettante  de dire a keycloak d'utiliser la config de spring pour a configuration
@Configuration
public class KeycloakAdapterConfig {
    @Bean
    public KeycloakSpringBootConfigResolver springBootConfigResolver()
    {
        return new KeycloakSpringBootConfigResolver();
    }

}
