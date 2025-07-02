package com.example.springbootlibraryapi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springbootlibraryapi.model.Book;
import com.example.springbootlibraryapi.service.GoogleLibraryService;

import reactor.core.publisher.Mono;

@Service
public class GoogleLibraryServiceImpl implements GoogleLibraryService {

    private final int ISBN10 = 10;
    private final int ISBN13 = 13;

    @Autowired
    private WebClient webClient;

    public Book fetchBookByIsbn(String isbn) {
        Mono<Map<String, Object>> response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("www.googleapis.com")
                .path("/books/v1/volumes")
                .queryParam("q", "isbn:" + isbn)
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});

        Map<String, Object> data = response.block();
        if (data == null || !data.containsKey("items")) return null;

        List<Map<String, Object>> items = (List<Map<String, Object>>) data.get("items");
        if (items.isEmpty()) return null;

        Map<String, Object> volumeInfo = (Map<String, Object>) items.get(0).get("volumeInfo");

        String title = (String) volumeInfo.get("title");
        List<String> authors = (List<String>) volumeInfo.get("authors");
        String author = authors != null && !authors.isEmpty() ? authors.get(0) : "Unknown";

        String publishDate = (String) volumeInfo.get("publishedDate");

        return Book.builder()
            .title((String) volumeInfo.get("title"))
            .isbn10((getIsbn(volumeInfo.get("industryIdentifiers"), ISBN13))
            .isbn13((getIsbn(volumeInfo.get("industryIdentifiers"), ISBN10))
            .authors()
            .publisher()
            .publishDate()
            .description()
            .categories()
            .cover()
            .build();
    }

    private String getIsbn(Map<String, Object> indeustryIdenfitiers, int isbnType) {
        if(isbnType == ISBN10) {
            
        }
        return "";
    }
    
}
