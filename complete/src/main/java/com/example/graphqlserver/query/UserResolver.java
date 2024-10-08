package com.example.graphqlserver.query;

import com.example.graphqlserver.domain.Status;
import com.example.graphqlserver.dto.Blog;
import com.example.graphqlserver.dto.Comment;
import com.example.graphqlserver.dto.User;
import com.example.graphqlserver.dto.UserInput;
import com.example.graphqlserver.mapper.BlogMapper;
import com.example.graphqlserver.mapper.CommentMapper;
import com.example.graphqlserver.mapper.UserMapper;
import com.example.graphqlserver.store.BlogStore;
import com.example.graphqlserver.store.CommentStore;
import com.example.graphqlserver.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserResolver {

    private final UserStore userStore;
    private final BlogStore blogStore;
    private final CommentStore commentStore;

    @QueryMapping
    public List<User> users() {
        return userStore.getAllStreamed()
                .map(user -> UserMapper.map(user, blogStore.getIdsBy(user.id())))
                .toList();
    }

    @QueryMapping
    public User user(@Argument String id) {
        var user = userStore.getBy(id);
        var blogIds = blogStore.getIdsBy(id);
        return UserMapper.map(user, blogIds);
    }

    @QueryMapping("firstUser")
    public User first() {
        var user = userStore.first();
        return UserMapper.map(user, blogStore.getIdsBy(user.id()));
    }

    @QueryMapping
    public List<User> usersBy(@Argument String firstNamePart) {
        return userStore.getAllStreamed()
                .filter(userEntity -> userEntity.firstname().contains(firstNamePart))
                .map(user -> UserMapper.map(user, blogStore.getIdsBy(user.id())))
                .toList();
    }

    @QueryMapping("usersByStatus")
    public List<User> usersBy(@Argument Status status) {
        return userStore.getAllStreamed()
                .filter(userEntity -> userEntity.status() == status)
                .map(user -> UserMapper.map(user, blogStore.getIdsBy(user.id())))
                .toList();
    }

    @SchemaMapping(field = "blogs")
    public List<Blog> blogsBy(User user) {
        return BlogMapper.map(blogStore.getAllBy(user.id()));
    }

    @SchemaMapping(field = "comments")
    public List<Comment> commentsBy(User user) {
        return CommentMapper.map(commentStore.getAllBy(user.id()));
    }

    @MutationMapping
    public User add(@Argument UserInput user) {
        var userAdded = UserMapper.map(user);
        userStore.add(userAdded);
        return UserMapper.map(userAdded);
    }

}
