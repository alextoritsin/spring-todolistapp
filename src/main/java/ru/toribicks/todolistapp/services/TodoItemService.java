package ru.toribicks.todolistapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.toribicks.todolistapp.models.TodoItem;
import ru.toribicks.todolistapp.repositories.TodoItemRepository;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoItemService {

  @Autowired
  private TodoItemRepository todoItemRepository;

  public Iterable<TodoItem> getAllTodoItems() {
    return todoItemRepository.findAll();
  }

  public Optional<TodoItem> getTodoItemById(Long id) {
    return todoItemRepository.findById(id);
  }

  public TodoItem saveTodoItem(TodoItem todoItem) {
     if (Objects.isNull(todoItem.getId())) {
       todoItem.setCreatedAt(Instant.now());
     }

     todoItem.setUpdatedAt(Instant.now());
     return todoItemRepository.save(todoItem);
  }

  public void deleteTodoItem(TodoItem todoItem) {
    todoItemRepository.delete(todoItem);
  }
}
