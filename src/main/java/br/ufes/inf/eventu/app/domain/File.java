package br.ufes.inf.eventu.app.domain;

import br.ufes.inf.eventu.app.domain.enums.AttachmentType;
import org.springframework.http.MediaType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
