package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private String path;

    @Enumerated(EnumType.ORDINAL)
    @Getter @Setter private AttachmentType attachmentType;

    @Getter @Setter private String mimetype;
}
