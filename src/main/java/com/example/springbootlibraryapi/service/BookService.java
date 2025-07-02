package com.example.springbootlibraryapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springbootlibraryapi.model.Book;

@Service
public interface BookService {

    public Book findByIsbn(String isbn);

    public Book addBookByIsbn(String isbn);

    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public Book addBook(Book book);

    public Book updateBook(Long id, Book updatedBook);

    public void deleteBook(Long id);
}
