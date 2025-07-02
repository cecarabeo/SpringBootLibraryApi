package com.example.springbootlibraryapi.model.GoogleBooks;

import java.util.List;

import lombok.Data;

@Data
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks;
    private List<IndustryIdentifier> industryIdentifiers;
    private String description;
    private List<String> categories;
    private String publisher;
}
