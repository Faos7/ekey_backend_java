package com.ekey.repository;

import com.ekey.models.Role;
import com.ekey.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by re5 on 20.10.16.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findManyByRole(Role role);

    Optional<User> findOneByPhoneNumb(Long phoneNumb);
    User findOneByUsername(String username);
    User findOneByStudentCardId(Long studentCardId);

    Optional<User> findOneByNumberRFID(String numberRFID);
}
