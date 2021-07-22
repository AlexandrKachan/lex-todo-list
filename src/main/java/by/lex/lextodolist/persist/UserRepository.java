package by.lex.lextodolist.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   // контейнер Optional если пользователя нет
    Optional<User> findByUsername(String username);//spring создает запрос к БД который извлекает пользователя по имени
}
