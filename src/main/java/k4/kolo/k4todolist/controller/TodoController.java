package k4.kolo.k4todolist.controller;

import k4.kolo.k4todolist.entity.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import k4.kolo.k4todolist.service.TodoService;

import java.util.List;

@RestController
public class TodoController {
    private final TodoService service = new TodoService();
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }
}

