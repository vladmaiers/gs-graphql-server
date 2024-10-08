package com.example.graphqlserver.dto;

import lombok.Data;

@Data
public final class BlogFilter {

    private String title;
    private String content;
    private Boolean published;

}
