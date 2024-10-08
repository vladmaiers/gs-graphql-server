package com.example.graphqlserver.query;

import com.example.graphqlserver.dto.Blog;
import com.example.graphqlserver.dto.Comment;
import com.example.graphqlserver.dto.User;
import com.example.graphqlserver.mapper.BlogMapper;
import com.example.graphqlserver.mapper.CommentMapper;
import com.example.graphqlserver.mapper.UserMapper;
import com.example.graphqlserver.store.BlogStore;
import com.example.graphqlserver.store.CommentStore;
import com.example.graphqlserver.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class CommentResolver {

    private final CommentStore commentStore;
    private final BlogStore blogStore;
    private final UserStore userStore;

    @QueryMapping
    public List<Comment> comments() {
        return commentStore.getAllStreamed()
                .map(CommentMapper::map)
                .toList();
    }

    @SchemaMapping(field = "blog")
    public Blog blogBy(Comment comment) {
        return BlogMapper.map(blogStore.getBy(comment.blogId()));
    }

    @SchemaMapping(field = "commentator")
    public User userBy(Comment comment) {
        var user = userStore.getBy(comment.userId());
        return UserMapper.map(user, blogStore.getIdsBy(user.id()));
    }

}
