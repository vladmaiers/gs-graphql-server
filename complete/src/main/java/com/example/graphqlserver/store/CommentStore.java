package com.example.graphqlserver.store;

import com.example.graphqlserver.domain.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.graphqlserver.store.BlogStore.BLOG_ID_1;
import static com.example.graphqlserver.store.BlogStore.BLOG_ID_2;
import static com.example.graphqlserver.store.BlogStore.BLOG_ID_3;
import static com.example.graphqlserver.store.UserStore.USER_ID_1;
import static com.example.graphqlserver.store.UserStore.USER_ID_2;
import static com.example.graphqlserver.store.UserStore.USER_ID_3;

@Component
public class CommentStore {

    private static final String COM_ID_1 = "com-1";
    private static final String COM_ID_2 = "com-2";
    private static final String COM_ID_3 = "com-3";
    private static final String COM_ID_4 = "com-4";
    private static final String COM_ID_5 = "com-5";

    private static final TreeMap<String, CommentEntity> comments = new TreeMap<>(Map.of(
            COM_ID_1, new CommentEntity(COM_ID_1, "Comment 1", BLOG_ID_1, USER_ID_3),
            COM_ID_2, new CommentEntity(COM_ID_2, "Comment 2", BLOG_ID_2, USER_ID_1),
            COM_ID_3, new CommentEntity(COM_ID_3, "Comment 3", BLOG_ID_3, USER_ID_2),
            COM_ID_4, new CommentEntity(COM_ID_4, "Comment 4", BLOG_ID_1, USER_ID_3),
            COM_ID_5, new CommentEntity(COM_ID_5, "Comment 5", BLOG_ID_2, USER_ID_1)
    ));

    public Stream<CommentEntity> getAllStreamed() {
        return comments.values().stream();
    }

    public List<CommentEntity> getAllFor(String blogId) {
        return getAllStreamed()
                .filter(comment -> comment.blogId().equals(blogId))
                .toList();
    }

    public List<CommentEntity> getAllBy(String userId) {
        return getAllStreamed()
                .filter(comment -> comment.userId().equals(userId))
                .toList();
    }

}
