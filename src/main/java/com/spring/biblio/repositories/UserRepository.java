package com.spring.biblio.repositories;

import com.spring.biblio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNomContainingIgnoreCase(String nom);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

}
