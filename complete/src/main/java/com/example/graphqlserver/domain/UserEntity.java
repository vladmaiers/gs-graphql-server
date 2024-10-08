package com.example.graphqlserver.domain;

public record UserEntity(
        String id,
        String firstname,
        String email,
        Integer yearOfBirth,
        Status status
) { }
