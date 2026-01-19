package br.com.swsoftware.rangoapp.service;

import br.com.swsoftware.rangoapp.persistence.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(Long id, User user);

    void changePassword(Long id, String newPassword);

    User validateLogin(String login, String password);

    List<User> findByName(String name);

}
