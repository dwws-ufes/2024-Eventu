package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor
public class TicketGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter
    private BigDecimal price;

    @Setter
    private String description;

    @Setter
    @Column(name = "createdAt", columnDefinition = "TIME")
    private LocalTime createdAt;

    @Setter
    private String uuid;
}