package com.example.graphqlserver.mapper;

import com.example.graphqlserver.domain.BlogEntity;
import com.example.graphqlserver.dto.Blog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BlogMapper {

    public static Blog map(BlogEntity blog) {
        if (blog == null) return null;

        return new Blog(
                blog.id(),
                blog.title(),
                blog.content(),
                blog.published(),
                blog.userId()
        );
    }

    public static List<Blog> map(List<BlogEntity> blogs) {
        return blogs.stream()
                .map(BlogMapper::map)
                .toList();
    }
}
