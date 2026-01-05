package com.example.springbootlibraryapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springbootlibraryapi.dto.BookRequestDto;
import com.example.springbootlibraryapi.dto.BookResponseDto;
import com.example.springbootlibraryapi.model.Book;
import com.example.springbootlibraryapi.service.BookService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/find-by-isbn")
    public BookResponseDto getFindByIsbn(@RequestParam String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return mapToResponseDto(book);
    }

    @PostMapping("/add-by-isbn")
    public BookResponseDto postAddByIsbn(@RequestParam String isbn) {
        Book book = bookService.addBookByIsbn(isbn);
        return mapToResponseDto(book);
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return mapToResponseDto(book);
    }

    @PostMapping
    public BookResponseDto addBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        Book book = mapToEntity(bookRequestDto);
        Book savedBook = bookService.addBook(book);
        return mapToResponseDto(savedBook);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        Book book = mapToEntity(bookRequestDto);
        Book updatedBook = bookService.updateBook(id, book);
        return mapToResponseDto(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    private BookResponseDto mapToResponseDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn13(book.getIsbn13());
        dto.setIsbn10(book.getIsbn10());
        dto.setAuthors(book.getAuthors());
        dto.setPublisher(book.getPublisher());
        dto.setPublishDate(book.getPublishDate());
        dto.setDescription(book.getDescription());
        dto.setCategories(book.getCategories());
        dto.setCover(book.getCover());
        return dto;
    }

    private Book mapToEntity(BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn13(dto.getIsbn13());
        book.setIsbn10(dto.getIsbn10());
        book.setAuthors(dto.getAuthors());
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublishDate());
        book.setDescription(dto.getDescription());
        book.setCategories(dto.getCategories());
        book.setCover(dto.getCover());
        return book;
    }
}
