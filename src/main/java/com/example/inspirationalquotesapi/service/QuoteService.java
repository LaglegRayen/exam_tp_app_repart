package com.example.inspirationalquotesapi.service;

import com.example.inspirationalquotesapi.model.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    private final List<Quote> quotes = new ArrayList<>();
    private final Random random = new Random();

    public List<Quote> getAllQuotes() {
        return quotes;
    }

    public Optional<Quote> getRandomQuote() {
        if (quotes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(quotes.get(random.nextInt(quotes.size())));
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public boolean deleteQuoteById(Long id) {
        return quotes.removeIf(quote -> quote.getId().equals(id));
    }
}