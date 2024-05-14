package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.TicketGroup;
import org.springframework.data.repository.CrudRepository;

public interface TicketGroupDAO extends CrudRepository<TicketGroup, Long> {
}
