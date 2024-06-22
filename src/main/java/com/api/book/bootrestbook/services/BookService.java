package com.api.book.bootrestbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService 
{

    @Autowired
    BookRepository bookRepository;


    //Create

    public void create(Book book)
    {
        bookRepository.save(book);
    }


    //Read

    /*

         public Book getOneBook(int id) 
         {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.get();
        }
     
     */

     public Optional<Book> getOneBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook;
    }

    public Optional<Iterable<Book>> getAllBooks() 
    {
        //Optional.of(bookRepository.findAll()) -->Optional.of is added to create Optional of it
        //Not true, but kind of typecasting
        Optional<Iterable<Book>> optionalBooks = Optional.of(bookRepository.findAll());
        return optionalBooks;
    }


    //Update

    //This method gets the job done but can create some unwanted results
    //Like duplicate entries if id not specified again in JSON, apart from url

    /* 

    public void update (Book book, int id)
    {
        bookRepository.save(book);
    }

    */

    //Using this, you could edit entry of a single column
    //Don't need to worry about other column being null

    /*

    public void update(Book book, int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            if (book.getTitle()!=null) existingBook.setTitle(book.getTitle());
            if (book.getAuthor()!= null) existingBook.setAuthor(book.getAuthor());
            bookRepository.save(existingBook);
        }
    }

    */

    public Book update(Book book, int id) 
    {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book existingBook = optionalBook.get();
        if (book.getTitle()!=null) existingBook.setTitle(book.getTitle());
        if (book.getAuthor()!= null) existingBook.setAuthor(book.getAuthor());
        bookRepository.save(existingBook);
        return existingBook;
        }
    



    //Deletion

    public void delete (int id)
    {
        bookRepository.deleteById(id);
    }

}
