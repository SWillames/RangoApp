package br.com.swsoftware.rangoapp.application.dto;

import br.com.swsoftware.rangoapp.domain.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String login;
    private String address;
    private UserType type;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
}
