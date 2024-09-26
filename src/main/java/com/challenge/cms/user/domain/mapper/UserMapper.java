package com.challenge.cms.user.domain.mapper;

import com.challenge.cms.user.domain.command.UserCommand;
import com.challenge.cms.user.domain.model.User;
import com.challenge.cms.user.presentation.UserJson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser (UserCommand userCommand);
    UserJson toUserJson (User user);
}
