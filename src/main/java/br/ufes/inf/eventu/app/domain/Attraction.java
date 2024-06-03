package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private String description;

    @Getter @Setter private LocalTime createdAt;

    @ManyToOne // Indicates a Many-to-One relationship
    @JoinColumn(name = "attraction_type_id") // Name of the foreign key column
    @Getter @Setter private AttractionType attractionType;

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Getter @Setter private Set<AttractionTime> attractionTimes = new HashSet<>();
}
