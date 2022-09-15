package com.eshop.bookservice.command.event;


import com.eshop.bookservice.command.data.Book;
import com.eshop.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {
    @Autowired
    private BookRepository repository;

    @EventHandler
    public void on(BookCreateEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        repository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event){
        Book book = repository.getById(event.getBookId());
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setReady(event.getReady());
        repository.save(book);
    }

    @EventHandler
    public void on(BookDeletedEvent event){
        repository.deleteById(event.getBookId());
    }

}
