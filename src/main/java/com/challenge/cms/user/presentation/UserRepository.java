package com.challenge.cms.user.presentation;

import com.challenge.cms.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
