package com.example.graphqlserver.store;

import com.example.graphqlserver.domain.Status;
import com.example.graphqlserver.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@Component
public class UserStore {

    static final String USER_ID_1 = "user-1";
    static final String USER_ID_2 = "user-2";
    static final String USER_ID_3 = "user-3";

    private static final TreeMap<String, UserEntity> users = new TreeMap<>(Map.of(
            USER_ID_1, new UserEntity(USER_ID_1, "John", "john@gmail.com", 1990, Status.REGISTERED),
            USER_ID_2, new UserEntity(USER_ID_2, "Sam", "sam@gmail.com", 1988, Status.ACTIVE),
            USER_ID_3, new UserEntity(USER_ID_3, "Lana", "lana@gmail.com", 1995, Status.DISABLED)
    ));

    public Stream<UserEntity> getAllStreamed() {
        return users.values().stream();
    }

    public UserEntity getBy(String id) {
        return getAllStreamed().filter(user -> user.id().equals(id)).findFirst().orElse(null);
    }

    public UserEntity first() {
        return users.firstEntry().getValue();
    }

    public void add(UserEntity user) {
         users.put(user.id(), user);
    }

}
