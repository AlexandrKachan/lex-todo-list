package by.lex.lextodolist.persist;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    List<TodoItem> findByUserUsername(String username);


}
