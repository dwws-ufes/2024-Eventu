package br.ufes.inf.eventu.app.model;

import java.util.HashSet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpeakerModel {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 255, message = "Título deve conter entre 2 a 255 caracteres")
    private String name;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 0, max = 255, message = "Descrição deve conter entre 0 a 255 caracteres")
    private String description;

    @NotBlank(message = "Estado de nascimento é obrigatório")
    @Size(min = 0, max = 255, message = "Estado de nascimento deve conter entre 0 a 255 caracteres")
    private String birthPlace;
}
