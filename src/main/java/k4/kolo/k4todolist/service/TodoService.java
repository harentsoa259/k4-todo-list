package k4.kolo.k4todolist.service;

import k4.kolo.k4todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoDao todoDao = new TodoDao();

    public List<Todo> getAllTodos() {
        return todoDao.getAll();
    }

    public Optional<Todo> getTodoById(int id) {
        return todoDao.getById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoDao.create(todo);
    }
}