package com.example.tinylinks.entity;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;

    private String publisher;

    private Book(BookBuilder builder) {
        this.author = builder.author;
        this.title = builder.title;
        this.publisher = builder.publisher;
    }

    @Data
    public static class BookBuilder {
        private String title;
        private String author;

        private String publisher;

        public BookBuilder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public BookBuilder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
