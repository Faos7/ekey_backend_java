package com.stepping.step5.service;

import com.stepping.step5.entity.CurrentUser;
import com.stepping.step5.entity.User;
import com.stepping.step5.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by re5 on 24.10.16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findOneByUsername(username).get();
            return new CurrentUser(user);
        }catch (Exception e){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }

    }
}
