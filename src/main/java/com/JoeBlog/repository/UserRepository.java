package com.JoeBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JoeBlog.model.User;

@Repository
@Transactional(readOnly=true)
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	@Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.accountEnabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
