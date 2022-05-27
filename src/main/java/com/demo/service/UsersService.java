package com.demo.service;

import com.demo.models.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService implements IUserService {
    private final RealmResource realm;

    @Override
    public List<UserRepresentation> getAllUsers() {
        return realm.users().list();
    }

    @Override
    @SuppressWarnings("java:S112")
    public UserRepresentation getOneUser(String key) {
        return getAllUsers().stream()
                .filter(u -> u.getId().equals(key) || u.getUsername().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No found user by username " + key));

    }

    @Override
    public String createUser(UserRequest data) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(data.getUsername());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        if (data.getAttributes() != null) {
            user.setAttributes(data.getAttributes());
        }

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue("test");

        String userId = "";
        try (Response response = realm.users().create(user)) {
            if (response.getStatus() < 200 || response.getStatus() >= 300) {
                log.warn("Error creating user ({}): {}", response.getStatus(), response.getStatusInfo().getReasonPhrase());
            }
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                userId = CreatedResponseUtil.getCreatedId(response);
                log.info("Register keycloak client status: {}", response.getStatus());
            }
        }
        return "User created with userId: %s".formatted(userId);

    }

}
