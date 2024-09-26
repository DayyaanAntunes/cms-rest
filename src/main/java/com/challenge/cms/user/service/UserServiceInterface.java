package com.challenge.cms.user.service;

import com.challenge.cms.user.domain.command.UserCommand;
import com.challenge.cms.user.domain.model.User;

public interface UserServiceInterface {
    User save(UserCommand userCommand);
    User update(UserCommand userCommand);
    User findById(Long id);
}
