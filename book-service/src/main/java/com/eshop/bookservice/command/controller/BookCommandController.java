package com.eshop.bookservice.command.controller;

import com.eshop.bookservice.command.command.CreateBookCommand;
import com.eshop.bookservice.command.command.DeleteBookCommand;
import com.eshop.bookservice.command.command.UpdateBookCommand;
import com.eshop.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(),
                model.getName(),
                model.getAuthor(),
                true);
        commandGateway.send(command);
        return "Add Book";
    }

    @PutMapping
    public String updateBook(@RequestBody BookRequestModel model) {
        UpdateBookCommand command = new UpdateBookCommand(model.getBookId(),
                model.getName(),
                model.getAuthor(),
                model.getReady());
        commandGateway.send(command);
        return "Update Book";
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        commandGateway.send(command);
        return "Delete Book";
    }
}
