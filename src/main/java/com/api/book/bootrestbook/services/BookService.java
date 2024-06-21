package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.Repository.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    // private static List<Book> list = new ArrayList<>();
    
    // static{
    //     list.add(new Book(123,"learn java","xyz"));
    //     list.add(new Book(456,"learn javascript","abc"));
    //     list.add(new Book(789,"learn python","pqr"));
    // }

    public List<Book> getAllBooks(){
        List<Book> li = (List<Book>) bookRepository.findAll();
        return li ;
    }

    public Book getBookById(int id){
        Book b = null ;
        try{
            // b= list.stream().filter(e->e.getId()==id).findFirst().get();
            b = bookRepository.getBookById(id);
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return b;
     
    }
    
    public Book addBook(Book book){
        // list.add(book);


        return bookRepository.save(book);
    }

    public void DeleteBook(int id) {
        try{
            // list = list.stream().filter(e->e.getId()!=id).collect(Collectors.toList());

            bookRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
     
    }

    public void updateBook(int id, Book book){

        // list = list.stream().map(b->{
        //     if(b.getId()==id){
        //         b.setTitle(book.getTitle());
        //         b.setAuthor(book.getAuthor());
              
        //     }
        //     return b;
        // }).collect(Collectors.toList());

         book.setId(id);
        bookRepository.save(book);
    }
}
