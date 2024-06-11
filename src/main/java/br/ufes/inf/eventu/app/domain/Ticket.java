package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.TicketStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter
    private TicketStatus status;

    @Setter
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    @Setter private User user;
}
