package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.email = :email")
    User findByEmail(String email);

}
