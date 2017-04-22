package com.ekey.controller;

import com.ekey.models.Book;
import com.ekey.models.Library;
import com.ekey.models.User;
import com.ekey.repository.BooksRepository;
import com.ekey.repository.LibraryRepository;
import com.ekey.repository.TransactionRepository;
import com.ekey.repository.UserRepository;
import com.ekey.models.Transaction;
import com.ekey.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;


/**
 * {@link Book} REST controller
 *
 * @author faos7
 * @version 1.8
 */

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    TransactionRepository transactionRepository;


    /**
     * Get All {@link Book}s
     * @return Json with all {@link Book}s
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> getAllBooks(){
        return new ResponseEntity<>((Collection<Book>) booksRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Get {@link Book} with id
     * @param id
     * @return Json with {@link Book} which has id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable int id){
        return new ResponseEntity<>(booksRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Add new {@link Book} to database
     * @param name - {@link Book} name
     * @param author - {@link Book} book author
     * @param number - {@link Book} inventory number
     * @param year - {@link Book} publication year
     * @param lId - {@link Book}'s {@link Library} id
     * @return String. if string is equal to "Transaction success" then {@link Book}
     * is added to database, and is marked as free. else - error
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createBook(String name, String author, String number, int year, int lId){
        try{
            Library library = libraryRepository.findOne(lId);
            Book book = new Book();
            book.setFree(true);
            book.setAuthorName(author);
            book.setBookName(name);
            book.setNumber(number);
            book.setLibrary(library);
            book.setPublYear(year);
            library.addBook(book);
            libraryRepository.save(library);
            booksRepository.save(book);
        }catch (Exception ex){
            return "Error creating the book: " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * Give {@link Book} to {@link User} student
     * @param id - {@link Library} id
     * @param number - {@link Book} inventory number
     * Both id and number gives unic inventory number, stored in database
     * @param username - {@link User} librarian username
     * @param studentCardId - {@link User} student's RFID
     * @param dateTo - {@link Book} - date, when studenr should return {@link Book} to {@link Library}
     * dateFrom is server current date
     * @return String. if string is equal to "Transaction success" then {@link Book}
     * is given to {@link User} student, and marked as in use. else - error
     */
    @RequestMapping(value = "/give", method =  RequestMethod.POST)
    @ResponseBody
    public String giveBookToStudent(int id, String number, String username, Long studentCardId, Date dateTo){
        try {
            User student = userRepository.findOneByStudentCardId(studentCardId);
            User librarian = userRepository.findOneByUsername(username);
            Book book = booksRepository.findOneByNumber(id + number).get();
            Transaction transaction = new Transaction();

            transaction.setBook(book);
            transaction.setLibrarian(librarian);
            transaction.setStudent(student);
            transaction.setDateFrom(new Date());
            transaction.setFinished(false);

            book.setFree(false);
            book.setDateFrom(new Date());
            book.setDateTo(dateTo);
            book.setUser(student);
            student.addBook(book);

            transactionRepository.save(transaction);
            booksRepository.save(book);
            userRepository.save(student);
        }catch (Exception ex){
            return "Error giving book " + number + " to student " + studentCardId + ": " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * Take {@link Book} from {@link User} student
     * @param id - {@link Library} id
     * @param number - {@link Book} inventory number
     * Both id and number gives unic inventory number, stored in database
     * @param studentCardId - {@link User} student's RFID
     * @return String. if string is equal to "Transaction success" then {@link Book}
     * is taken from {@link User} student, and marked as free. else - error
     */
    @RequestMapping(value = "/get", method = RequestMethod.PUT)
    @ResponseBody
    public String getBookFromTheStudent(Long id, String number, Long studentCardId){
        try {
            User student = userRepository.findOneByStudentCardId(studentCardId);
            Book book = booksRepository.findOneByNumber(id + number).get();
            Transaction transaction = transactionService.getTransactionByBookAndStudent(student, book);

            transaction.setDateTo(new Date());
            transaction.setFinished(true);

            book.setFree(true);
            book.setDateTo(null);
            book.setUser(null);
            book.setDateFrom(null);

            student.deleteBook(book);
            userRepository.save(student);
            booksRepository.save(book);
            transactionRepository.save(transaction);
        }catch (Exception ex){
            return "Error getting book " + number + " from student " + studentCardId + ": " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * Deletes {@link Book} from database
     * @param id - {@link Library} id
     * @param number - {@link Book} inventory number
     * Both id and number gives unic inventory number, stored in database
     * @return String. if string is equal to "Transaction success" then {@link Book}
     * is deleted from database. else - error
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBook(int id, String number){
        try{
            Book book = booksRepository.findOneByNumber(id + number).get();
            User user = book.getUser();
            Library library = libraryRepository.findOne(id);
            library.deleteBook(book);
            user.deleteBook(book);
            userRepository.save(user);
            libraryRepository.save(library);
            booksRepository.delete(book);
        }catch (Exception ex)
        {
            return "Error deleting the book: " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * Get all {@link Book}s {@link User} student ever owned
     * @param id - {@link User} student id
     * @return Json with all {@link Book} {@link User} student ever had
     */
    @RequestMapping(value = "/student/ever/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>> getAllBooksStudentEverHad(@PathVariable Long id){
        return new ResponseEntity<Collection<Book>>(transactionService.getAllBooksStudentEverHad(id), HttpStatus.OK);
    }

    /**
     * Get all {@link Book}s {@link User} librarian ever given
     * @param id - {@link User} librarian id
     * @return Json with all {@link Book} {@link User} librarian ever given
     */
    @RequestMapping(value = "/librarian/ever/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>> getAllBooksLibrarianEverGiven(@PathVariable Long id){
        return new ResponseEntity<Collection<Book>>(transactionService.getAllBooksLibrarianEverGiven(id), HttpStatus.OK);

    }

    /**
     * Get all {@link Book}s {@link User} student ownes
     * @param id - {@link User} student id
     * @return Json with all {@link Book} {@link User} student ownes
     */
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>> getAllStudentBooks(@PathVariable Long id){
        return new ResponseEntity<Collection<Book>>(userRepository.findOne(id).getBooks(), HttpStatus.OK);

    }

    /**
     * Get all {@link Book}s in {@link Library}
     * @param id - {@link Library} id
     * @return Json with all {@link Book}s in {@link Library}
     */
    @RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Book>>getAllLibraryBooks(@PathVariable int id){
        return new ResponseEntity<Collection<Book>>(libraryRepository.findOne(id).getBooks(), HttpStatus.OK);
    }
}
