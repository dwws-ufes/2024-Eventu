package br.ufes.inf.eventu.app.model;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufes.inf.eventu.app.domain.File;

@Getter
@Setter
@NoArgsConstructor
public class AttractionModel {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 255, message = "Título deve conter entre 2 a 255 caracteres")
    private String name;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 255, message = "Descrição deve conter entre 2 a 255 caracteres")
    private String description;

    private Long attractionTypeId;

    private Set<Long> speakersIds;

    private Set<File> attachments;
    
}
