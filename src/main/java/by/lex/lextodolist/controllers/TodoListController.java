package by.lex.lextodolist.controllers;

import by.lex.lextodolist.persist.TodoItem;
import by.lex.lextodolist.persist.TodoItemRepository;
import by.lex.lextodolist.persist.User;
import by.lex.lextodolist.persist.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TodoListController {
    private static final Logger logger = LoggerFactory.getLogger(TodoListController.class);

    private final TodoItemRepository repository;
    private final UserRepository userRepository;


    @Autowired
    //конструктор который дает доступ к репозиторию
    public TodoListController(TodoItemRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {

        logger.info("Имя пользователя :{} ", principal.getName());

        model.addAttribute("items", repository.findByUserUsername(principal.getName())); //привязка каждому пользователю своего списка по username
        model.addAttribute("item", new TodoItem());
        return "index";
    }

    @PostMapping
    public String newTodoItem(TodoItem todoItem, Principal principal) {

        logger.info("Имя пользователя :{} ", principal.getName());

        User user = userRepository.findByUsername(principal.getName()).get();
        todoItem.setUser(user);

        repository.save(todoItem);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }


}
