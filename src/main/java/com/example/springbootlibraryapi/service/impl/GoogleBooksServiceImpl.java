package com.example.springbootlibraryapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springbootlibraryapi.model.Book;
import com.example.springbootlibraryapi.model.GoogleBooks.GoogleBooksResponse;
import com.example.springbootlibraryapi.model.GoogleBooks.IndustryIdentifier;
import com.example.springbootlibraryapi.model.GoogleBooks.VolumeInfo;
import com.example.springbootlibraryapi.service.GoogleBooksService;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {

    private final String ISBN10 = "ISBN_10";
    private final String ISBN13 = "ISBN_13";

    @Autowired
    private WebClient webClient;

    public Book fetchBookByIsbn(String isbn) {
        GoogleBooksResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("www.googleapis.com")
                .path("/books/v1/volumes")
                .queryParam("q", "isbn:" + isbn)
                .build())
            .retrieve()
            .bodyToMono(GoogleBooksResponse.class)
            .block();

        if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
            return null;
        }

        VolumeInfo volumeInfo = response.getItems().get(0).getVolumeInfo();

        return Book.builder()
            .title(volumeInfo.getTitle())
            .isbn10(getIsbn(volumeInfo.getIndustryIdentifiers(), ISBN13))
            .isbn13(getIsbn(volumeInfo.getIndustryIdentifiers(), ISBN10))
            .authors(volumeInfo.getAuthors())
            .publisher(volumeInfo.getPublisher())
            .publishDate(volumeInfo.getPublishedDate())
            .description(volumeInfo.getDescription())
            .categories(volumeInfo.getCategories())
            .cover(volumeInfo.getImageLinks().getThumbnail())
            .build();
    }

    private String getIsbn(List<IndustryIdentifier> identifiers, String isbnType) {
        return identifiers.stream()
            .filter(id -> isbnType.equals(id.getType()))
            .map(IndustryIdentifier::getIdentifier)
            .findFirst()
            .orElse("");
    }
    
}
