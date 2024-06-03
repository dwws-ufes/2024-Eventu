package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class AttractionUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    @Getter @Setter private Attraction attraction;
}
