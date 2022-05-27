package com.demo.service;

import com.demo.models.UserRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IUserService {
    List<UserRepresentation> getAllUsers();

    @Nullable
    UserRepresentation getOneUser(String id);

    String createUser(UserRequest data);

}
