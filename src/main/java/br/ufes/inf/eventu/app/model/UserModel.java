package br.ufes.inf.eventu.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve conter entre 2 a 255 caracteres")
    private String fullName;

    @NotNull(message = "Email é obrigatório")
    @NotEmpty(message = "Email é obrigatório")
    @Size(min = 2, max = 255, message = "Email deve conter entre 2 a 255 caracteres")
    private String email;

    private LocalDate dateBirth;

    @NotEmpty(message = "Instituição é obrigatório")
    @Size(min = 2, max = 255, message = "Instituição deve conter entre 2 a 255 caracteres")
    private String institution;

    @NotEmpty(message = "Senha é obrigatório")
    private String password;
}