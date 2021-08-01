package by.lex.lextodolist.security;

import by.lex.lextodolist.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthService  implements UserDetailsService { //имплементим сервис для проверки есть ли в БД такой юзер

    private final UserRepository repository;

    @Autowired
    public UserAuthService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username) //этот метод возвращает optional
                .map(user -> new User( // с помощью метода map результат который был возвращен с помощью лямбда выражений преобразовать в User из пакетв Spr Sec
                        user.getUsername(), // параметр имя пользователя
                        user.getPassword(), // пароль
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                ))
                .orElseThrow(() ->new UsernameNotFoundException("Пользователь не найден"));
    }
}
