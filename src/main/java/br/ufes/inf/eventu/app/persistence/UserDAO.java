package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {
}
