package com.stepping.step5.repository;

import com.stepping.step5.models.Role;
import com.stepping.step5.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by re5 on 20.10.16.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findManyByRole(Role role);
    User findOneByUsername(String username);
}
