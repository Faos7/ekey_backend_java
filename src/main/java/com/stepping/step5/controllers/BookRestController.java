package com.stepping.step5.controllers;

import com.stepping.step5.entity.models.Book;
import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.models.Student;
import com.stepping.step5.entity.repository.BooksRepository;
import com.stepping.step5.entity.repository.LibraryRepository;
import com.stepping.step5.entity.repository.StudentRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @RequestMapping("/")
    @ResponseBody
    public String getAllBooks(){
        ArrayList<Book> collection = new ArrayList<>();
        try{
            collection.addAll(booksRepository.findAll());
        }catch (Exception ex){
            return "can't get list with books: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Book book: collection) {
                res += book.toString();
            }
            return res;
        }else return "There is no book!";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getBookWithId(int id){
        Book book;
        try {
            book = booksRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find book: " + ex.toString();
        }
        return book.toString();

    }

    @RequestMapping("/create")
    @ResponseBody
    public String createBook(String name, String author, int year, int sId, int lId){
        try{
            Student student = studentRepository.findOne(sId);
            Library library = libraryRepository.findOne(lId);
            Book book = new Book();
            book.setAuthorName(author);
            book.setBookName(name);
            book.setStudent(student);
            book.setLibrary(library);
            book.setPublYear(year);
            student.addBook(book);
            library.addBook(book);
            studentRepository.save(student);
            libraryRepository.save(library);
            booksRepository.save(book);
        }catch (Exception ex){
            return "Error creating the book: " + ex.toString();
        }
        return "Book succesfully created!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteBook(int id){
        try{
            Book book = booksRepository.findOne(id);
            Student student = book.getStudent();
            Library library = book.getLibrary();
            library.deleteBook(book);
            student.deleteBook(book);
            studentRepository.save(student);
            libraryRepository.save(library);
            booksRepository.delete(book);
        }catch (Exception ex)
        {
            return "Error deleting the book: " + ex.toString();
        }
        return "Book succesfully deleted!";
    }

    @RequestMapping("/student")
    @ResponseBody
    public String getAllStudentBooks(int id){
        ArrayList<Book> books = new ArrayList<>();
        try{
            Student student = studentRepository.findOne(id);
            books.addAll(student.getBooks());
        }catch (Exception ex){
            return "Can't get all student books: " + ex.toString();
        }
        String res = "";
        if (books.size()!=0){
            for(Book book: books){
                res += book.toString();
            }
            return res;
        }else
            return "This student has no books!";
    }

    @RequestMapping("/library")
    @ResponseBody
    public String getAllLibraryBooks(int id){
        ArrayList<Book> books = new ArrayList<>();
        try{
            Library library = libraryRepository.findOne(id);
            books.addAll(library.getBooks());
        }catch (Exception ex){
            return "Can't get all library books: " + ex.toString();
        }
        String res = "";
        if (books.size()!=0){
            for(Book book: books){
                res += book.toString();
            }
            return res;
        }else
            return "This library has no books!";
    }
}
