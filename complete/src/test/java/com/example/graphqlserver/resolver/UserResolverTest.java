package com.example.graphqlserver.resolver;

import com.example.graphqlserver.query.UserResolver;
import com.example.graphqlserver.store.BlogStore;
import com.example.graphqlserver.store.CommentStore;
import com.example.graphqlserver.store.UserStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@GraphQlTest({UserResolver.class, UserStore.class, BlogStore.class, CommentStore.class})
class UserResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void first() {
        assertDoesNotThrow(() ->
                this.graphQlTester
                        .documentName("firstUser")
                        .execute()
                        .path("firstUser")
                        .matchesJson("""
                                        {
                                            "id": "user-1",
                                            "firstname": "John",
                                            "email": "john@gmail.com",
                                            "yearOfBirth": 1990,
                                            "blogs": [
                                             {
                                               "id": "blog-1",
                                               "title": "Blog 1",
                                               "content": "Blog 1 content"
                                             }
                                            ]
                                        }
                                """));
    }
}
