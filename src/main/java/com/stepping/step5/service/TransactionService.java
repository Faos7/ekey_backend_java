package com.stepping.step5.service;

import com.stepping.step5.models.Book;
import com.stepping.step5.models.Transaction;
import com.stepping.step5.models.User;
import com.stepping.step5.models.out.UserOut;

import java.util.Collection;

/**
 * Created by faos7 on 12.11.16.
 */
public interface TransactionService {
    Collection<Book> getAllBooksStudentEverHad(Long studentCardId);
    Collection<Book> getAllBooksLibrarianEverGiven(Long studentCardId);
    Collection<UserOut> getAllBookOwners(String number);
    Transaction getTransactionByBookAndStudent(User student, Book book);
}
