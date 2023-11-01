package com.example.graphql.controller;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepo;
import com.example.graphql.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    public AuthorController(AuthorRepo authorRepo,BookRepo bookRepo){
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @QueryMapping
    Iterable<Author> authors(){
    return authorRepo.findAll();
    }
    @QueryMapping
    Optional<Author> authorById(@Argument Long id){
        return authorRepo.findById(id);
    }
    @MutationMapping
    Book addBook(@Argument BookInput book){
        Author author = authorRepo.findById(book.authorId()).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Book b = new Book(book.title(), book.publisher(), author);
        return bookRepo.save(b);
    }
    record BookInput(String title,String publisher,Long authorId){}
}
