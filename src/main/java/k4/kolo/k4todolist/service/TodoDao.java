package k4.kolo.k4todolist.service;

import k4.kolo.k4todolist.entity.Todo;
import k4.kolo.k4todolist.utils.Database;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoDao {

    public List<Todo> getAll() {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT id, title, description, created_at, deadline FROM todos";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                Instant due_date = rs.getTimestamp("due_date").toInstant();

                todos.add(new Todo(id, title, description, createdAt, due_date));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return todos;
    }

    public Optional<Todo> getById(int id) {
        String sql = "SELECT id, title, description, created_at, deadline FROM todos WHERE id = ?";
    
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                return Optional.of(new Todo(
                    id,
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getTimestamp("created_at").toInstant(),
                    rs.getTimestamp("deadline").toInstant()
                ));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    

    public Todo create(Todo todo) {
        String sql = "INSERT INTO todos (title, description, created_at, deadline) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
            statement.setTimestamp(3, Timestamp.from(todo.getCreatedAt()));
            statement.setTimestamp(4, Timestamp.from(todo.getDue_date()));
    
            if (statement.executeUpdate() > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        return new Todo(
                            keys.getInt(1),
                            todo.getTitle(),
                            todo.getDescription(),
                            todo.getCreatedAt(),
                            todo.getDue_date()
                        );
                    }
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

