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
public class AttractionTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    @Getter @Setter private Attraction attraction;

    @Setter
    @Getter
    private LocalTime start;

    @Setter
    @Getter
    private LocalTime finish;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @Getter @Setter private Location location;
}
