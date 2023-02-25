package ru.toribicks.todolistapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "todo_items")
public class TodoItem implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotEmpty(message = "Task should not be empty")
  @Size(min = 4, message = "Description must be at least 4 characters")
  private String description;
  private Boolean isComplete;
  private Instant createdAt;

  @Override
  public String toString() {
    return String.format("TodoItem{id=%d, description='%s', isComplete='%s'," +
        " createdAt='%s'}",
      id, description, isComplete, createdAt);
  }
}
