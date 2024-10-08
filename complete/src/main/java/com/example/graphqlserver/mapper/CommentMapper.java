package com.example.graphqlserver.mapper;

import com.example.graphqlserver.domain.CommentEntity;
import com.example.graphqlserver.dto.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentMapper {

    public static Comment map(CommentEntity comment) {
        if (comment == null) return null;

        return new Comment(
                comment.id(),
                comment.content(),
                comment.blogId(),
                comment.userId()
        );
    }

    public static List<Comment> map(List<CommentEntity> comments) {
        return comments.stream()
                .map(CommentMapper::map)
                .toList();
    }

}
