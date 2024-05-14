package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketDAO extends CrudRepository<Ticket, Long> {
}
