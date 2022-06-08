package com.example.demo.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    //methode prmettant de definir la strategie de gestion de la session
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(keycloakAuthenticationProvider()); //qui montrera a spring security cmt il va gerer les user et les role
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);//on garde la configuration par defaut
        http.authorizeRequests().antMatchers("/products/**","/suppliers/**").authenticated(); //on demande une authentification pour acceder a ces end-points

    }


}
