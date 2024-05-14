package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionType;
import org.springframework.data.repository.CrudRepository;

public interface AttractionTypeDAO extends CrudRepository<AttractionType, Long> {
}
