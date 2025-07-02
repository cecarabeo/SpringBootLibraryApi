package com.example.springbootlibraryapi.service;

import com.example.springbootlibraryapi.model.Book;

public interface GoogleLibraryService {
    
    public Book fetchBookByIsbn(String isbn);

}
