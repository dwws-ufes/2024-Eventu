package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Attraction;
import org.springframework.data.repository.CrudRepository;

public interface AttractionDAO extends CrudRepository<Attraction, Long> {
}
