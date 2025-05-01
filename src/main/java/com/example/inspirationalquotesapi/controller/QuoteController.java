package com.example.inspirationalquotesapi.controller;

import com.example.inspirationalquotesapi.model.Quote;
import com.example.inspirationalquotesapi.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        Optional<Quote> randomQuote = quoteService.getRandomQuote();
        return randomQuote.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PostMapping
    public ResponseEntity<String> addQuote(@RequestBody Quote quote) {
        quoteService.addQuote(quote);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quote added successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable Long id) {
        boolean removed = quoteService.deleteQuoteById(id);
        if (removed) {
            return ResponseEntity.ok("Quote deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quote not found.");
        }
    }
}