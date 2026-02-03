package br.com.swsoftware.rangoapp.application.dto;

import br.com.swsoftware.rangoapp.domain.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Login é obrigatório")
    private String login;

    @NotBlank(message = "Senha é obrigatório")
    private String password;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private UserType type;
}
