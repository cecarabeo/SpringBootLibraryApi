package com.example.springbootlibraryapi.service;

import com.example.springbootlibraryapi.model.Book;

public interface GoogleBooksService {
    
    public Book fetchBookByIsbn(String isbn);

}
