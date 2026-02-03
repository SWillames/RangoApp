package br.com.swsoftware.rangoapp.domain.repository;

import br.com.swsoftware.rangoapp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByLogin(String login);

    List<User> findByNameContainingIgnoreCase(String name);

}
