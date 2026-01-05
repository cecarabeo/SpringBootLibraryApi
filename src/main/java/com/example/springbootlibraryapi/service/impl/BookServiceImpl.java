package com.example.springbootlibraryapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootlibraryapi.model.Book;
import com.example.springbootlibraryapi.repository.BookRepository;
import com.example.springbootlibraryapi.service.BookService;
import com.example.springbootlibraryapi.service.GoogleBooksService;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired GoogleBooksService googleBooksService;

    public Book findByIsbn(String isbn) {
        return googleBooksService.fetchBookByIsbn(isbn);
    }

    public Book addBookByIsbn(String isbn) {
        Book book = googleBooksService.fetchBookByIsbn(isbn);

        if(book != null) return bookRepository.save(book);
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
                existing.setAuthors(updatedBook.getAuthors());
                return bookRepository.save(existing);
            })
            .orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsByIsbn13(String isbn13) {
        return bookRepository.existsByIsbn13(isbn13);
    }

    @Override
    public boolean existsByIsbn10(String isbn10) {
        return bookRepository.existsByIsbn10(isbn10);
    }
}
