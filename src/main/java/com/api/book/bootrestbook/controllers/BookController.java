package com.api.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;


    @GetMapping("/books")
   
    public ResponseEntity<List<Book>> getBook(){
    
        List<Book> b = bookService.getAllBooks();
        if(b.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(b));
        }
    }

    @GetMapping("/books/{id}")

    public ResponseEntity<Book> getBookById(@PathVariable ("id") int id){

        Book b =  bookService.getBookById(id);
        if(b==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.of(Optional.of(b));
        }
      
    }
    @PostMapping("/books")

    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try{
             b = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
        }

      
    }

    @DeleteMapping("/books/{id}")

    public ResponseEntity<Void> DeleteBook(@PathVariable("id") int id){
        try{
            bookService.DeleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    @PutMapping("/books/{id}")

    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        try{
            bookService.updateBook(id, book);
            return ResponseEntity.ok().body(book);

        }catch(Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

}
