package com.example.springbootlibraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootlibraryapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
