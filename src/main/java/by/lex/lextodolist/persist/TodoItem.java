package by.lex.lextodolist.persist;


import javax.persistence.*;
//данные для БД
@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // не допустима пустая строка
    private String name;

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
}
