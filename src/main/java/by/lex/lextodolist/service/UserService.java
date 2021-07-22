package by.lex.lextodolist.service;


import by.lex.lextodolist.persist.User;
import by.lex.lextodolist.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder; // шифрование пароля введенного при регистрации

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    //метод получения логина и пароля

    public void create(UserRepresentation userRepresentation) {
        User user = new User();
        user.setUsername(userRepresentation.getUsername()); //получаем логин
        user.setPassword(passwordEncoder.encode(userRepresentation.getPassword())); // получаем и декодируем пароль
        repository.save(user);


    }


}
