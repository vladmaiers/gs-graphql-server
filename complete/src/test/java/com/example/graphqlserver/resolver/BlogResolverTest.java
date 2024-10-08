package com.example.graphqlserver.resolver;

import com.example.graphqlserver.query.BlogResolver;
import com.example.graphqlserver.store.BlogStore;
import com.example.graphqlserver.store.CommentStore;
import com.example.graphqlserver.store.UserStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@GraphQlTest({BlogResolver.class, BlogStore.class, UserStore.class, CommentStore.class})
class BlogResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void blogs() {
        assertDoesNotThrow(() ->
                this.graphQlTester
                        .documentName("blogs")
                        .execute()
                        .path("blogs")
                        .matchesJson("""
                                [
                                  {
                                    "id": "blog-1",
                                    "title": "Blog 1",
                                    "content": "Blog 1 content",
                                    "creator": {
                                      "id": "user-1",
                                      "firstname": "John",
                                      "email": "john@gmail.com",
                                      "yearOfBirth": 1990
                                    },
                                    "comments": [
                                      {
                                        "content": "Comment 1"
                                      },
                                      {
                                        "content": "Comment 4"
                                      }
                                    ]
                                  },
                                  {
                                    "id": "blog-2",
                                    "title": "Blog 2",
                                    "content": "Blog 2 content",
                                    "creator": {
                                      "id": "user-2",
                                      "firstname": "Sam",
                                      "email": "sam@gmail.com",
                                      "yearOfBirth": 1988
                                    },
                                    "comments": [
                                      {
                                        "content": "Comment 2"
                                      },
                                      {
                                        "content": "Comment 5"
                                      }
                                    ]
                                  },
                                  {
                                    "id": "blog-3",
                                    "title": "Blog 3",
                                    "content": "Blog 3 content",
                                    "creator": {
                                      "id": "user-3",
                                      "firstname": "Lana",
                                      "email": "lana@gmail.com",
                                      "yearOfBirth": 1995
                                    },
                                    "comments": [
                                      {
                                        "content": "Comment 3"
                                      }
                                    ]
                                  }
                                ]
                                
                                """)
        );
    }
}
