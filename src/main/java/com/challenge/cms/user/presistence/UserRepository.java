package com.challenge.cms.user.presistence;

import com.challenge.cms.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE is_deleted = true",
            nativeQuery = true)
    List<User> findByIsDeleted();

    @Query(value = "SELECT * FROM users WHERE is_deleted = true AND id = :deleted_id",
            nativeQuery = true)
    User findDeletedById(@Param("deleted_id") Long id);
}
