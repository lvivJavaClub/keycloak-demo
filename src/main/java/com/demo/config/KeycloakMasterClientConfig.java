package com.demo.config;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakMasterClientConfig {
    private final KeycloakSpringBootProperties keycloakProperties;
    private final AppProperties appProperties;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .realm(appProperties.getKeycloak().getAdminRealm())
                .username(appProperties.getKeycloak().getUsername())
                .password(appProperties.getKeycloak().getPassword())
                .clientId(appProperties.getKeycloak().getClient())
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                )
                .build();

    }

    @Bean
    public RealmResource realmResource() {
        return keycloak().realm(keycloakProperties.getRealm());
    }

}
