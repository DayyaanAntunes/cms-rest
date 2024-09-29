package com.challenge.cms.user.service;

import com.challenge.cms.user.domain.command.UserCommand;
import com.challenge.cms.user.domain.model.User;
import java.util.List;

public interface UserServiceInterface {
    User save(UserCommand userCommand);
    User update(Long id, UserCommand userCommand);
    User findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    List<User> findAllDeletedUsers();
    User restoreUser(Long id);

}
