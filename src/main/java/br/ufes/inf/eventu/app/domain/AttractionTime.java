package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Setter
    @Column(name = "start", columnDefinition = "TIME")
    private LocalTime start;

    @Setter
    @Column(name = "finish", columnDefinition = "TIME")
    private LocalTime finish;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @Setter private Location location;

    @Override
    public String toString() {
        return date + " " + start + " às  " + finish + ", " + location.getName();
    }
}
