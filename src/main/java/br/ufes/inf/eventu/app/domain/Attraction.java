package br.ufes.inf.eventu.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

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
}
