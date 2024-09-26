package com.challenge.cms.user.service;

import com.challenge.cms.configuration.exception.ResponseException;
import com.challenge.cms.user.domain.command.UserCommand;
import com.challenge.cms.user.domain.mapper.UserMapper;
import com.challenge.cms.user.domain.model.User;
import com.challenge.cms.user.presistence.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService,UserServiceInterface {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserCommand userCommand) {
        User user = userMapper.toUser(userCommand);
        user.setPassword(new BCryptPasswordEncoder().encode(userCommand.password()));
        return saveOrUpdate(user);
    }

    private User saveOrUpdate(User user) {
        try{
            return userRepository.save(user);
        }catch (ConstraintViolationException e){
                throw new ResponseException("user.email-already-exists",HttpStatus.CONFLICT);
        }
    }

    @Override
    public User update(Long id, UserCommand userCommand) {
        User user = findById(id);
        userMapper.toModel(userCommand, user);
        return saveOrUpdate(user);
    }

    @Override
    public User findById(Long id) {
        try {
            return userRepository.findUserById(id);
        }catch (Exception e) {
            throw new ResponseException("user.not-found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if(userRepository.existsById(id)) userRepository.deleteById(id);
        throw new ResponseException("user.not-found", HttpStatus.NOT_FOUND);
    }

}
