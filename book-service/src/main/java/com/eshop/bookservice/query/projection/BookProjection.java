package com.eshop.bookservice.query.projection;

import com.eshop.bookservice.command.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;
}
