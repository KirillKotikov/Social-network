package ru.kotikov.springbootmvcproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.springbootmvcproject.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
