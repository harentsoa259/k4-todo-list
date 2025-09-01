package k4.kolo.k4todolist.service;

import k4.kolo.k4todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoDao todoDao = new TodoDao();

    public List<Todo> getAllTodos() {
        return todoDao.getAll();
    }
}

