package com.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, List<String>> attributes  = null;
}
