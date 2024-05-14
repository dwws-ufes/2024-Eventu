package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionUser;
import org.springframework.data.repository.CrudRepository;

public interface AttractionUserDAO extends CrudRepository<AttractionUser, Long> {
}
