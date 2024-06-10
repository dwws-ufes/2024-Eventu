package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter private String name;

    @Setter private String description;

    @Column(name = "createdAt", columnDefinition = "TIME")
    @Setter private LocalTime createdAt;

    @ManyToOne
    @JoinColumn(name = "attraction_type_id", nullable = false)
    @Setter private AttractionType attractionType;

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Setter private Set<AttractionTime> attractionTimes = new HashSet<>();

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Setter private Set<File> attachments = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_user",
        joinColumns = @JoinColumn(name = "attraction_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Setter private Set<User> registeredParticipants = new HashSet<>();
}
