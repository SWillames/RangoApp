package br.com.swsoftware.rangoapp.application;

import br.com.swsoftware.rangoapp.application.dto.LoginDTO;
import br.com.swsoftware.rangoapp.application.dto.UserResponseDTO;
import br.com.swsoftware.rangoapp.application.mapper.UserMapper;
import br.com.swsoftware.rangoapp.persistence.User;
import br.com.swsoftware.rangoapp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Tag(
        name = "Autenticação",
        description = "Endpoint responsável pela autenticação de usuários"
    )
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        User user = userService.validateLogin(loginDTO.getLogin(), loginDTO.getPassword());

        return ResponseEntity.ok(userMapper.toResponse(user));
    }
}
