package com.example.demo.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//classe permettante  de dire a keycloak d'utiliser la config de spring pour a configuration
@Configuration
public class KeycloakAdapterConfig {
    //methode permettante  de dire a keycloak d'utiliser la config de spring pour a configuration
    @Bean
    public KeycloakSpringBootConfigResolver springBootConfigResolver()
    {
        return new KeycloakSpringBootConfigResolver();
    }

    //methode permettant la communication entre nos deux projets en utilisant keycloak
    @Bean
    KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory)
    {
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }
}
