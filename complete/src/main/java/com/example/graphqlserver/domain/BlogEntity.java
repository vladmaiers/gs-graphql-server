package com.example.graphqlserver.domain;

public record BlogEntity(
        String id,
        String title,
        String content,
        boolean published,
        String userId
) { }
