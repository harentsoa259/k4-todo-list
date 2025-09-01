package k4.kolo.k4todolist.service;

import k4.kolo.k4todolist.entity.Todo;
import k4.kolo.k4todolist.utils.Database;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
}

