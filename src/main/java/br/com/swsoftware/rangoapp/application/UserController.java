package br.com.swsoftware.rangoapp.application;

import br.com.swsoftware.rangoapp.application.dto.UserCreateDTO;
import br.com.swsoftware.rangoapp.application.dto.UserPasswordDTO;
import br.com.swsoftware.rangoapp.application.dto.UserResponseDTO;
import br.com.swsoftware.rangoapp.application.dto.UserUpdateDTO;
import br.com.swsoftware.rangoapp.application.mapper.UserMapper;
import br.com.swsoftware.rangoapp.persistence.User;
import br.com.swsoftware.rangoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userDto) {
        User updatedUser = userService.update(id, userDto);
        return ResponseEntity.ok(userMapper.toResponse(updatedUser));
    }

    @PatchMapping("{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDTO dto){
        userService.changePassword(id, dto.getNewPassword());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findByName(@RequestParam String name) {
        List<UserResponseDTO> users = userService.findByName(name)
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);

    }
}
