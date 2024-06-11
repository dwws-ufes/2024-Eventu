package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter
    private String fullName;

    @Setter
    @Column(unique = true)
    private String email;

    @Setter
    private UserRole role;

    @Setter
    @Column(name = "dateBirth", columnDefinition = "DATE")
    private LocalDate dateBirth;

    @Setter
    private String institution;

    @Setter
    private String password;

    @Setter
    private String passwordSalt;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "attraction_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    @Setter private Set<Attraction> registeredAttractions = new HashSet<>();
}