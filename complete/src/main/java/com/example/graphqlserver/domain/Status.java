package com.example.graphqlserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    REGISTERED("Registered (pending activation)"),
    ACTIVE( "Active"),
    DISABLED( "Disabled"),
    EXPIRED( "Expired");

    private final String description;

}
