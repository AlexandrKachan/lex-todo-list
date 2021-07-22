package by.lex.lextodolist.persist;


import javax.persistence.*;
//данные для БД
@Entity // сущность хранящаяся в БД
@Table(name = "todo_items") // имя таблицы
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // не допустима пустая строка
    private String name;


    @ManyToOne // связываем пользователей и их дела
    private User user;

//конструктор
    public TodoItem() {
    }
//гетеры и сетеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
