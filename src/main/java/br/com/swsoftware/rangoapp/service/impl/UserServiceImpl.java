package br.com.swsoftware.rangoapp.service.impl;

import br.com.swsoftware.rangoapp.persistence.User;
import br.com.swsoftware.rangoapp.repository.UserRepository;
import br.com.swsoftware.rangoapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setUserType(updatedUser.getUserType());

        return userRepository.save(existingUser);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public User validateLogin(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("Login inválido"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return user;
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
