package org.caloidoscope.vaxseen.repository;


import org.caloidoscope.vaxseen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,
        RevisionRepository<User, Long, Integer> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByUsernameIn(List<String> usernames);
}
