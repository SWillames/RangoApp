package br.com.swsoftware.rangoapp.service.impl;

import br.com.swsoftware.rangoapp.application.dto.UserUpdateDTO;
import br.com.swsoftware.rangoapp.application.mapper.UserMapper;
import br.com.swsoftware.rangoapp.exception.BusinessException;
import br.com.swsoftware.rangoapp.exception.ResourceNotFoundException;
import br.com.swsoftware.rangoapp.domain.model.User;
import br.com.swsoftware.rangoapp.domain.repository.UserRepository;
import br.com.swsoftware.rangoapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, UserUpdateDTO updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        userMapper.updateEntity(updatedUser, existingUser);

        return userRepository.save(existingUser);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (newPassword == null || newPassword.isBlank()) {
            throw new BusinessException("Nova senha não pode ser vazia");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public User validateLogin(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new BusinessException("Login ou senha inválidos"));

        if (!user.getPassword().equals(password)) {
            throw new BusinessException("Login ou Senha inválidos");
        }

        return user;
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
