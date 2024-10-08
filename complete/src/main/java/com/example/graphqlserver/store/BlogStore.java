package com.example.graphqlserver.store;

import com.example.graphqlserver.domain.BlogEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.graphqlserver.store.UserStore.USER_ID_1;
import static com.example.graphqlserver.store.UserStore.USER_ID_2;
import static com.example.graphqlserver.store.UserStore.USER_ID_3;

@Component
public class BlogStore {

    static final String BLOG_ID_1 = "blog-1";
    static final String BLOG_ID_2 = "blog-2";
    static final String BLOG_ID_3 = "blog-3";

    private static final TreeMap<String, BlogEntity> blogs = new TreeMap<>(Map.of(
            BLOG_ID_1, new BlogEntity(BLOG_ID_1, "Blog 1", "Blog 1 content", true, USER_ID_1),
            BLOG_ID_2, new BlogEntity(BLOG_ID_2, "Blog 2", "Blog 2 content", true, USER_ID_2),
            BLOG_ID_3, new BlogEntity(BLOG_ID_3, "Blog 3", "Blog 3 content", false, USER_ID_3)
    ));

    public Stream<BlogEntity> getAllStreamed() {
        return blogs.values().stream();
    }

    public List<BlogEntity> getAll() {
        return getAllStreamed().toList();
    }

    public BlogEntity getBy(String id) {
        return getAllStreamed()
                .filter(blogEntity -> blogEntity.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<BlogEntity> getAllBy(String userId) {
        return getAllStreamed()
                .filter(blog -> blog.userId().equals(userId))
                .toList();
    }

    public Set<String> getIdsBy(String userId) {
        return getAllStreamed()
                .filter(blog -> blog.userId().equals(userId))
                .map(BlogEntity::id)
                .collect(Collectors.toUnmodifiableSet());
    }

    public BlogEntity first() {
        return blogs.firstEntry().getValue();
    }

}
