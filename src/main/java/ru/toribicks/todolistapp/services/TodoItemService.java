package ru.toribicks.todolistapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.toribicks.todolistapp.models.TodoItem;
import ru.toribicks.todolistapp.repositories.TodoItemRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

  private final TodoItemRepository todoItemRepository;

  @Autowired
  public TodoItemService(TodoItemRepository todoItemRepository) {
    this.todoItemRepository = todoItemRepository;
  }

  public List<TodoItem> getAllTodoItems() {
    return todoItemRepository.findAll();
  }

  public Optional<TodoItem> getTodoItemById(Long id) {
    return todoItemRepository.findById(id);
  }

  public void saveTodoItem(TodoItem todoItem) {
     if (todoItem.getId() == 0) {
       todoItem.setCreatedAt(Instant.now());
     }

    todoItemRepository.save(todoItem);
  }

  public void deleteTodoItemById(Long id) {
    todoItemRepository.deleteById(id);
  }
}
