package br.ufes.inf.eventu.app.model;

import java.util.List;
import java.util.Set;

import br.ufes.inf.eventu.app.domain.File;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String topic;

    private Integer vagas;

    private Long attractionTypeId;

    private List<Long> speakersIds;

    private Set<File> attachments;

    private List<AttractionTimeModel> attractionTimes;
    
}
