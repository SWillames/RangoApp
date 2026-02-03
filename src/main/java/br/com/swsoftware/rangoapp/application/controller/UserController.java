package br.com.swsoftware.rangoapp.application.controller;

import br.com.swsoftware.rangoapp.application.dto.UserCreateDTO;
import br.com.swsoftware.rangoapp.application.dto.UserPasswordDTO;
import br.com.swsoftware.rangoapp.application.dto.UserResponseDTO;
import br.com.swsoftware.rangoapp.application.dto.UserUpdateDTO;
import br.com.swsoftware.rangoapp.application.mapper.UserMapper;
import br.com.swsoftware.rangoapp.domain.model.User;
import br.com.swsoftware.rangoapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
    name = "Usuários",
    description = "Endpoints responsáveis pelo gerenciamento de usuários"
)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(
        summary = "Cadastrar usuário",
        description = "Realiza o cadastro de um novo usuário no sistema"
    )
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(
        responseCode = "400",
        description = "Erro de validação ou regra de negócio",
        content = @Content(schema = @Schema(implementation = ProblemDetail.class))
    )
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(savedUser));
    }

    @Operation(
        summary = "Atualizar dados do usuário",
        description = "Atualiza os dados cadastrais do usuário, exceto a senha"
    )
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(
        responseCode = "404",
        description = "Usuário não encontrado",
        content = @Content(schema = @Schema(implementation = ProblemDetail.class))
    )
    @ApiResponse(
        responseCode = "400",
        description = "Erro de validação",
        content = @Content(schema = @Schema(implementation = ProblemDetail.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDTO userDto) {
        User updatedUser = userService.update(id, userDto);
        return ResponseEntity.ok(userMapper.toResponse(updatedUser));
    }

    @Operation(
        summary = "Alterar senha do usuário",
        description = "Altera a senha do usuário por meio de endpoint exclusivo"
    )
    @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso")
    @ApiResponse(
        responseCode = "404",
        description = "Usuário não encontrado",
        content = @Content(schema = @Schema(implementation = ProblemDetail.class))
    )
    @ApiResponse(
        responseCode = "400",
        description = "Erro de validação ou regra de negócio",
        content = @Content(schema = @Schema(implementation = ProblemDetail.class))
    )
    @PatchMapping("{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable("id") Long id, @Valid @RequestBody UserPasswordDTO dto){
        userService.changePassword(id, dto.getNewPassword());

        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Buscar usuários por nome",
        description = "Realiza a busca de usuários cujo nome contenha o valor informado"
    )
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findByName(@RequestParam(required = false) String name) {
        List<UserResponseDTO> users = userService.findByName(name)
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);

    }
}
