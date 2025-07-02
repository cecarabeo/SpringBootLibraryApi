package com.example.springbootlibraryapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootlibraryapi.model.Book;
import com.example.springbootlibraryapi.repository.BookRepository;
import com.example.springbootlibraryapi.service.BookService;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    public Book findByIsbn(String isbn) {
        return null;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
            .map(existing -> {
                existing.setTitle(updatedBook.getTitle());
                existing.setAuthor(updatedBook.getAuthor());
                return bookRepository.save(existing);
            })
            .orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
