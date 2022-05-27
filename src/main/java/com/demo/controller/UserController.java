package com.demo.controller;

import com.demo.models.RoleNames;
import com.demo.models.UserRequest;
import com.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @GetMapping
    @RolesAllowed({RoleNames.ROLE_USER, RoleNames.ROLE_ADMIN})
    public ResponseEntity<List<UserRepresentation>> readUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{key}")
    @RolesAllowed(RoleNames.ROLE_ADMIN)
    public ResponseEntity<UserRepresentation> getUserByKey(@PathVariable String key) {
        return ResponseEntity.ok(usersService.getOneUser(key));
    }

    @PostMapping
    @RolesAllowed(RoleNames.ROLE_ADMIN)
    public ResponseEntity<String> registerUser(@RequestBody UserRequest data) {
        return ResponseEntity.ok(usersService.createUser(data));
    }

}
