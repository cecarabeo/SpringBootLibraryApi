package com.example.springbootlibraryapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookResponseDto {

    private Long id;
    private String title;
    private String isbn13;
    private String isbn10;
    private List<String> authors;
    private String publisher;
    private String publishDate;
    private String description;
    private List<String> categories;
    private String cover;
}