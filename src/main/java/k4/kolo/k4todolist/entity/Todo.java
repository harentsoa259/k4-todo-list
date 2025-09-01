package k4.kolo.k4todolist.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Todo {
    private int id;
    private String title;
    private String description;
    private Instant createdAt;
    private Instant due_date;
}


