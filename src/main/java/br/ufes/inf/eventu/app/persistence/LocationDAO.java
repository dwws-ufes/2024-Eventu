package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationDAO extends CrudRepository<Location, Long> {
}
