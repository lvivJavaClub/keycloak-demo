package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
public class AppProperties {
    Keycloak keycloak = new Keycloak();

    public Keycloak getKeycloak() {
        return keycloak;
    }

    public void setKeycloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public static class Swagger{
        private String url;
        private String description;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Keycloak {
        private String adminRealm = "master";
        private String username;
        private String password;
        private String client = "admin-cli";

        public String getAdminRealm() {
            return adminRealm;
        }

        public void setAdminRealm(String adminRealm) {
            this.adminRealm = adminRealm;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }
    }
}
