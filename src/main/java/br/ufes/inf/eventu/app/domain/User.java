package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Setter
    @Getter
    private String fullName;

    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    private UserRole role;

    @Setter
    @Getter
    private LocalDate dateBirth;

    @Setter
    @Getter
    private String institution;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String passwordSalt;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    @Getter @Setter private Set<Attraction> registeredAttractions = new HashSet<>();
}