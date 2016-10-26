package com.stepping.step5.repository;

import com.stepping.step5.entity.Role;
import com.stepping.step5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by re5 on 20.10.16.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
    Collection<Optional<User>> findManyByRole(Role role);
    Optional<User> findOneByUsername(String username);
}
