package com.example.springbootlibraryapi.model.GoogleBooks;

import java.util.List;

import lombok.Data;

@Data
public class GoogleBooksResponse {
    private List<GoogleBookItem> items;
}
