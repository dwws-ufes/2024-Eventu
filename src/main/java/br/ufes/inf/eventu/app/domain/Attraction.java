package br.ufes.inf.eventu.app.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter private String name;

    @Setter private String description;

    @Setter private String topic;

    @Setter private Integer vagas;

    @Column(name = "createdAt", columnDefinition = "TIME")
    @Setter private LocalTime createdAt;

    @ManyToOne
    @JoinColumn(name = "attraction_type_id", nullable = false)
    @Setter private AttractionType attractionType;

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Setter private List<AttractionTime> attractionTimes = new ArrayList<>();

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    @Setter private Set<File> attachments = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_speaker",
        joinColumns = @JoinColumn(name = "attraction_id"),
        inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    @Setter private List<Speaker> speakers = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_user",
        joinColumns = @JoinColumn(name = "attraction_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Setter private Set<User> registeredParticipants = new HashSet<>();
}
