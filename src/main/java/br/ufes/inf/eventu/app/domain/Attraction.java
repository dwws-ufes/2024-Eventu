package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
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

    @ManyToOne
    @JoinColumn(name = "attraction_type_id")
    @Getter @Setter private AttractionType attractionType;

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Getter @Setter private Set<AttractionTime> attractionTimes = new HashSet<>();

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Getter @Setter private Set<File> attachments = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_user",
        joinColumns = @JoinColumn(name = "attraction_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Getter @Setter private Set<User> registeredParticipants = new HashSet<>();
}
