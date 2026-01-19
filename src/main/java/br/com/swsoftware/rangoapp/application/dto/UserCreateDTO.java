package br.com.swsoftware.rangoapp.application.dto;

import br.com.swsoftware.rangoapp.persistence.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    private String name;
    private String email;
    private String login;
    private String password;
    private String address;
    private UserType type;
}
