package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketDAO extends CrudRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.uuid = :code")
    List<Ticket> retrieveByTicketCode(String code);
}