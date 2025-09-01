package k4.kolo.k4todolist.controller;

import k4.kolo.k4todolist.entity.Todo;
import org.springframework.web.bind.annotation.*;
import k4.kolo.k4todolist.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service = new TodoService();
    
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable int id) {
        Optional<Todo> todo = service.getTodoById(id);
        
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new RuntimeException("Todo non trouvé avec l'id: " + id);
        }
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }
}