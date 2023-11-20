package ru.ydubovitsky.inctagrammockbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.inctagrammockbackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
