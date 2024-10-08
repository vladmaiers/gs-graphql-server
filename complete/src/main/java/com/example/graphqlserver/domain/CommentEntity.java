package com.example.graphqlserver.domain;

public record CommentEntity(
        String id,
        String content,
        String blogId,
        String userId
) { }
