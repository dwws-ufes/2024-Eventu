package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionTime;
import org.springframework.data.repository.CrudRepository;

public interface AttractionTimeDAO extends CrudRepository<AttractionTime, Long> {
}
