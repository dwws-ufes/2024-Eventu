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
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class AttractionTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @ManyToOne // Indicates a Many-to-One relationship
    @JoinColumn(name = "attraction_id") // Name of the foreign key column
    @Getter @Setter private Attraction attraction;

    @Setter
    @Getter
    private LocalTime start;

    @Setter
    @Getter
    private LocalTime finish;
}
