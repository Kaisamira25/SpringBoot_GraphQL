package com.example.graphql;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepo;
import com.example.graphql.repository.BookRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GraphQlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQlApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(AuthorRepo authorRepo, BookRepo bookRepo){
        return args -> {
            Author josh = authorRepo.save(new Author(null,"Josh-Long"));
            Author mark = authorRepo.save(new Author(null,"Mark-Que"));
            bookRepo.saveAll(List.of(
                    new Book("Book-1","Publisher-1",josh),
                    new Book("Book-2","Publisher-2",mark),
                    new Book("Book-3","Publisher-3",josh)
            ));
        };

    }
}

