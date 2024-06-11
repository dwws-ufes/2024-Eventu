package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class AttractionTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    @Setter private Attraction attraction;

    @Setter
    @Column(name = "start", columnDefinition = "DATETIME")
    private LocalDateTime start;

    @Setter
    @Column(name = "finish", columnDefinition = "DATETIME")
    private LocalDateTime finish;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @Setter private Location location;
}
