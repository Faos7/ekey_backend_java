package com.stepping.step5.controller;

import com.stepping.step5.models.Book;
import com.stepping.step5.models.Library;
import com.stepping.step5.models.User;
import com.stepping.step5.repository.BooksRepository;
import com.stepping.step5.repository.LibraryRepository;
import com.stepping.step5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> getAllBooks(){
        return new ResponseEntity<>((Collection<Book>) booksRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable int id){
        return new ResponseEntity<>(booksRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createBook(String name, String author, int year, Long sId, int lId){
        try{
            User user = userRepository.findOne(sId);
            Library library = libraryRepository.findOne(lId);
            Book book = new Book();
            book.setAuthorName(author);
            book.setBookName(name);
            book.setUser(user);
            book.setLibrary(library);
            book.setPublYear(year);
            user.addBook(book);
            library.addBook(book);
            userRepository.save(user);
            libraryRepository.save(library);
            booksRepository.save(book);
        }catch (Exception ex){
            return "Error creating the book: " + ex.toString();
        }
        return "Book succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBook(int id){
        try{
            Book book = booksRepository.findOne(id);
            User user = book.getUser();
            Library library = book.getLibrary();
            library.deleteBook(book);
            user.deleteBook(book);
            userRepository.save(user);
            libraryRepository.save(library);
            booksRepository.delete(book);
        }catch (Exception ex)
        {
            return "Error deleting the book: " + ex.toString();
        }
        return "Book succesfully deleted!";
    }



    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>> getAllStudentBooks(@PathVariable Long id){
        return new ResponseEntity<Collection<Book>>(userRepository.findOne(id).getBooks(), HttpStatus.OK);

    }

    @RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>>getAllLibraryBooks(@PathVariable int id){
        return new ResponseEntity<Collection<Book>>(libraryRepository.findOne(id).getBooks(), HttpStatus.OK);
    }
}
