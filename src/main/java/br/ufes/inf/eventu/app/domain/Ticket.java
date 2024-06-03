package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Setter
    @Getter
    private TicketStatus status;

    @Setter
    @Getter
    private String uuid;
}