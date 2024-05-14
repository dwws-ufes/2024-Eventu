package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Checkin;
import org.springframework.data.repository.CrudRepository;

public interface CheckinDAO extends CrudRepository<Checkin, Long> {
}
