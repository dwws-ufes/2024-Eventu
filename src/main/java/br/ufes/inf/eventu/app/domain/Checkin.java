package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor
public class Checkin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(name = "timestamp", columnDefinition = "TIME")
    private LocalTime timestamp;

    @ManyToOne
    @JoinColumn(name = "attraction_time_id")
    @Setter private AttractionTime attractionTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter private User user;
}

