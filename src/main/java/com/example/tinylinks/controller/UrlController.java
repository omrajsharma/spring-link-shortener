package com.example.tinylinks.controller;

import com.example.tinylinks.entity.Book;
import com.example.tinylinks.entity.Url;
import com.example.tinylinks.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping()
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestParam String originalUrl) {
        return ResponseEntity.ok(service.shortenUrl(originalUrl));
    }

    @GetMapping("/{urlCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String urlCode) {

//        Book book = new Book.BookBuilder("Half Blood Prince", "JKR").publisher("Hogwads").build();

        Optional<Url> urlOptional = service.getOriginalUrl(urlCode);

        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            return ResponseEntity
                    .status(302)
                    .location(URI.create(url.getOriginalUrl()))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
