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
