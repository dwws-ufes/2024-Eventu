package br.ufes.inf.eventu.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttractionTimeModel {

    private Long attractionId;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 255, message = "Título deve conter entre 2 a 255 caracteres")
    private String start;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 255, message = "Descrição deve conter entre 2 a 255 caracteres")
    private String finish;

    private Long locationId;
}
