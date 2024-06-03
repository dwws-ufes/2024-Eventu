package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.TicketGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketGroupDAO extends JpaRepository<TicketGroup, Long> {
}
