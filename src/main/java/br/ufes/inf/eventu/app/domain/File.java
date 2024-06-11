package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Setter private String name;

    @Setter private String path;

    @Enumerated(EnumType.ORDINAL)
    @Setter private AttachmentType attachmentType;

    @Setter private String mimetype;

    @ManyToOne 
    @JoinColumn(name = "attraction_id")
    @Setter private Attraction attraction;
}
