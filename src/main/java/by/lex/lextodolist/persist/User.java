package by.lex.lextodolist.persist;


import javax.persistence.*;
import java.util.List;

//данные для БД Пользователи

@Entity // сущность хранящаяся в БД
@Table(name = "users") // имя таблицы
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // не допустима пустая строка
    private String username;


    @Column(nullable = false) // не допустима пустая строка
    private String password;

    @OneToMany(
            mappedBy = "user", //мапим на поле юзер в связанной таблице
            cascade = CascadeType.ALL, //при удалении пользователя удаляем все связанное с ним
            orphanRemoval = true // не привязанные дела удаляются из БД


    )

    private List<TodoItem> todoItemList;


    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public void setTodoItemList(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }
}
