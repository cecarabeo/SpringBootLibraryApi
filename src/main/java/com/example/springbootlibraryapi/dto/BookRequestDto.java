package com.example.springbootlibraryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 13, message = "ISBN-13 must not exceed 13 characters")
    private String isbn13;

    @Size(max = 10, message = "ISBN-10 must not exceed 10 characters")
    private String isbn10;

    private List<String> authors;

    private String publisher;

    private String publishDate;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    private List<String> categories;

    private String cover;
}