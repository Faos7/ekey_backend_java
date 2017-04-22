package com.ekey.repository;

import com.ekey.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findOneByNumber(String Number);

    Collection<Optional<Book>> findManyByBookName(String bookName);

    Collection<Optional<Book>> findManyByAuthorName(String authorName);

    Collection<Optional<Book>> findManyByPublYear(int publYear);
}
