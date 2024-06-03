package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
public class Checkin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Setter
    @Getter
    private LocalTime timestamp;

    @ManyToOne
    @JoinColumn(name = "attraction_time_id")
    @Getter @Setter private AttractionTime attractionTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;
}

