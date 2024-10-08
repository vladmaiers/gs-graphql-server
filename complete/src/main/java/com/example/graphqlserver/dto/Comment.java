package com.example.graphqlserver.dto;

public record Comment(
        String id,
        String content,
        String blogId,
        String userId
) { }
