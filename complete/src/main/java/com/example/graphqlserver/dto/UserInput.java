package com.example.graphqlserver.dto;

import lombok.Data;

@Data
public final class UserInput {

    private String firstname;
    private String email;
    private Integer yearOfBirth;

}
