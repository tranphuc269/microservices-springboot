package com.eshop.bookservice.command.aggregate;

import com.eshop.bookservice.command.command.CreateBookCommand;
import com.eshop.bookservice.command.command.DeleteBookCommand;
import com.eshop.bookservice.command.command.UpdateBookCommand;
import com.eshop.bookservice.command.event.BookCreateEvent;
import com.eshop.bookservice.command.event.BookDeletedEvent;
import com.eshop.bookservice.command.event.BookUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
    public BookAggregate(){

    }

    @CommandHandler
    public BookAggregate(CreateBookCommand command){
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(command, bookCreateEvent);
        // phát đi event
        AggregateLifecycle.apply(bookCreateEvent);
    }


    @CommandHandler
    public void handle(UpdateBookCommand command){
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(command, bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand command){
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(command, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event){
        // khi apply thì nó sẽ nhảy vào hàm này
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent event){
        // khi apply thì nó sẽ nhảy vào hàm này
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getReady();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent event){
        // khi apply thì nó sẽ nhảy vào hàm này
        this.bookId = event.getBookId();
    }

}
