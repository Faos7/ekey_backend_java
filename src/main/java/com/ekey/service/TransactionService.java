package com.ekey.service;

import com.ekey.models.Book;
import com.ekey.models.Transaction;
import com.ekey.models.User;
import com.ekey.models.out.UserOut;

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
