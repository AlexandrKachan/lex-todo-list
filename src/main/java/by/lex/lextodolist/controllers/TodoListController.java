package by.lex.lextodolist.controllers;

import by.lex.lextodolist.persist.TodoItem;
import by.lex.lextodolist.persist.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoListController {

    private TodoItemRepository repository;

    @Autowired
    //конструктор который дает доступ к репозиторию
    public TodoListController(TodoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String indexPage(Model model){
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new TodoItem());
        return "index";
    }
    @PostMapping
    public String newTodoItem (TodoItem todoItem){
        repository.save(todoItem);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id){
        repository.deleteById(id);
        return "redirect:/";
    }




}
