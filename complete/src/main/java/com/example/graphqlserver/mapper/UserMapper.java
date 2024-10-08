package com.example.graphqlserver.mapper;

import com.example.graphqlserver.domain.Status;
import com.example.graphqlserver.domain.UserEntity;
import com.example.graphqlserver.dto.User;
import com.example.graphqlserver.dto.UserInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static User map(UserEntity user, Set<String> blogIds) {
        if (user == null) return null;

        return new User(
                user.id(),
                user.firstname(),
                user.email(),
                user.yearOfBirth(),
                user.status(),
                blogIds
        );
    }

    public static User map(UserEntity user) {
        return map(user, Set.of());
    }

    public static UserEntity map(UserInput user) {
        return new UserEntity(
                UUID.randomUUID().toString(),
                user.getFirstname(),
                user.getEmail(),
                user.getYearOfBirth(),
                Status.REGISTERED
        );
    }

}
