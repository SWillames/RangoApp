package br.com.swsoftware.rangoapp.application.mapper;

import br.com.swsoftware.rangoapp.application.dto.UserCreateDTO;
import br.com.swsoftware.rangoapp.application.dto.UserResponseDTO;
import br.com.swsoftware.rangoapp.application.dto.UserUpdateDTO;
import br.com.swsoftware.rangoapp.persistence.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserCreateDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public void updateEntity(UserUpdateDTO dto, User user) {
        modelMapper.map(dto, user);
    }

    public UserResponseDTO toResponse(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
