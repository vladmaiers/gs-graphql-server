package com.example.graphqlserver.dto;

import com.example.graphqlserver.domain.Status;

import java.util.Set;

public record User(
        String id,
        String firstname,
        String email,
        Integer yearOfBirth,
        Status status,
        Set<String> blogIds
) { }
