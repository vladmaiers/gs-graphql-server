package com.example.graphqlserver.query;

import com.example.graphqlserver.dto.Blog;
import com.example.graphqlserver.dto.BlogFilter;
import com.example.graphqlserver.dto.Comment;
import com.example.graphqlserver.dto.User;
import com.example.graphqlserver.mapper.BlogMapper;
import com.example.graphqlserver.mapper.CommentMapper;
import com.example.graphqlserver.mapper.UserMapper;
import com.example.graphqlserver.store.BlogStore;
import com.example.graphqlserver.store.CommentStore;
import com.example.graphqlserver.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class BlogResolver {

    private final BlogStore blogStore;
    private final UserStore userStore;
    private final CommentStore commentStore;

    @QueryMapping
    public List<Blog> blogs() {
        return BlogMapper.map(blogStore.getAll());
    }

    @QueryMapping
    public Blog blog(@Argument String id) {
        return BlogMapper.map(blogStore.getBy(id));
    }

    @QueryMapping("firstBlog")
    public Blog first() {
        var blog = blogStore.first();
        return BlogMapper.map(blog);
    }

    @QueryMapping
    public List<Blog> blogsBy(@Argument BlogFilter filter) {
        var blogsByFilter = blogStore.getAllStreamed()
                .filter(blog -> filter.getTitle() == null || blog.title().contains(filter.getTitle()))
                .filter(blog -> filter.getContent() == null || blog.content().contains(filter.getContent()))
                .filter(blog -> filter.getPublished() == null || blog.published() == filter.getPublished())
                .toList();
        return BlogMapper.map(blogsByFilter);
    }

    @SchemaMapping(field = "creator")
    public User userBy(Blog blog) {
        var user = userStore.getBy(blog.userId());
        return UserMapper.map(user, blogStore.getIdsBy(user.id()));
    }

    @SchemaMapping(field = "comments")
    public List<Comment> commentsFor(Blog blog) {
        return CommentMapper.map(commentStore.getAllFor(blog.id()));
    }

}
