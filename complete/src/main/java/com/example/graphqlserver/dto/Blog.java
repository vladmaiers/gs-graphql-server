package com.example.graphqlserver.dto;

public record Blog(
        String id,
        String title,
        String content,
        boolean published,
        String userId
) { }
