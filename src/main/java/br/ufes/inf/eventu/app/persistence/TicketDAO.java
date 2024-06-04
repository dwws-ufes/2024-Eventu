package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDAO extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.uuid = :code")
    List<Ticket> retrieveByTicketCode(String code);

    @Query(value = "SELECT t FROM Ticket t JOIN t.user u WHERE u.id = :userId", nativeQuery = true)
    List<Ticket> retrieveByUserId(long userId);
}
