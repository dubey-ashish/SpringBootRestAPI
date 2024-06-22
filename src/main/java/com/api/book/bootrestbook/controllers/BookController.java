package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    // We need object to use functions of CrudRepository
    @Autowired
    BookService bookService;

    // Create
    @PostMapping("/book")
    public void create(@RequestBody Book book) {
        bookService.create(book);
    }

    // Read

    // The code below didn't handle the status, ergo a better one follows it!

    /*
      
      @GetMapping(value="/book/{id}")
      public Book getBook(@PathVariable("id") int id)
      {
      return bookService.getOneBook(id) ;
      }
      
     */

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Optional<Book> book = bookService.getOneBook(id);
        return ResponseEntity.of(book);
    }

    /*
      
      @GetMapping("/book")
      public Iterable<Book> getAllBooks()
      {
      return bookService.getAllBooks();
      }
      
     */

    @GetMapping("/book")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Optional<Iterable<Book>> book = bookService.getAllBooks();
        return ResponseEntity.of(book);
    }

    // Update

    /*
      
      @PutMapping("book/{id}")
      public void update(@RequestBody Book book, @PathVariable("id") int id)
      {
      bookService.update(book, id);
      }
      
     */

     

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable int id) {
        Optional<Book> existingBook = bookService.getOneBook(id);
        if (!existingBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Book updatedBook = bookService.update(book, id);
        return ResponseEntity.ok(updatedBook);
    }



    // Delete

    /*

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }

    */


    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Book> existingBook = bookService.getOneBook(id);
        if (!existingBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            bookService.delete(id);
            return ResponseEntity.noContent().build(); // returns a 204 No Content status
        }
    }
    


}

