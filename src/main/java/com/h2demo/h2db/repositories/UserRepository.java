package com.h2demo.h2db.repositories;

import com.h2demo.h2db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user_db WHERE email=?1 and password=?2", nativeQuery = true)
    public Optional<User> findUserForLogin(String email, String pass);
}
