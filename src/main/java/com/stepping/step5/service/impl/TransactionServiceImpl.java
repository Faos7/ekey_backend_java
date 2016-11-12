package com.stepping.step5.service.impl;

import com.stepping.step5.models.Book;
import com.stepping.step5.models.Transaction;
import com.stepping.step5.models.User;
import com.stepping.step5.models.out.UserOut;
import com.stepping.step5.repository.BooksRepository;
import com.stepping.step5.repository.UserRepository;
import com.stepping.step5.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by faos7 on 12.11.16.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private UserRepository userRepository;
    private BooksRepository booksRepository;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository, BooksRepository booksRepository) {
        this.userRepository = userRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public Collection<Book> getAllBooksStudentEverHad(Long studentCardId) {
        User user = userRepository.findOneByStudentCardId(studentCardId);
        Collection<Transaction> transactions = user.getStTransactions();
        Collection<Book> res = new ArrayList<>();
        for (Transaction tx:transactions) {
            res.add(tx.getBook());
        }
        return res;
    }

    @Override
    public Collection<Book> getAllBooksLibrarianEverGiven(Long studentCardId) {
        User user = userRepository.findOneByStudentCardId(studentCardId);
        Collection<Transaction> transactions = user.getLbTransactions();
        Collection<Book> res = new ArrayList<>();
        for (Transaction tx:transactions) {
            res.add(tx.getBook());
        }
        return res;
    }

    @Override
    public Collection<UserOut> getAllBookOwners(String number) {
        Book book = booksRepository.findOneByNumber(number).get();
        Collection<Transaction> transactions = book.getTransactions();
        Collection<UserOut> res = new ArrayList<>();
        for (Transaction tx:transactions) {
            res.add(new UserOut(tx.getStudent()));
        }
        return res;
    }

    @Override
    public Transaction getTransactionByBookAndStudent(User student, Book book) {
        Collection<Transaction> transactions = student.getStTransactions();
        Transaction tx = new Transaction();
        for (Transaction transaction:transactions) {
            if (tx.isFinished() == false){
                if (transaction.getBook() == book){
                    tx = transaction;
                }
            }
        }
        return tx;
    }
}
