package ru.toribicks.todolistapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.toribicks.todolistapp.models.TodoItem;
import ru.toribicks.todolistapp.services.TodoItemService;

@Controller
public class HomeController {
  @Autowired
  private TodoItemService todoItemService;

  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("todoItems", todoItemService.getAllTodoItems());
    return modelAndView;
  }

  @GetMapping("/create-todo")
  public String showCreateForm(TodoItem todoItem) {
    return "todo-item";
  }

  @PostMapping("/todo")
  public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
    TodoItem item = new TodoItem();

    item.setDescription(todoItem.getDescription());
    item.setIsComplete(todoItem.getIsComplete());

    todoItemService.saveTodoItem(todoItem);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String deleteTodoItem(@PathVariable("id") Long id) {
    TodoItem todoItem = todoItemService.getTodoItemById(id)
      .orElseThrow(() -> new IllegalArgumentException("Todo Item with id " + id + "does not exist"));

    todoItemService.deleteTodoItem(todoItem);
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String updateTodoItem(@PathVariable("id") Long id, Model model) {
    TodoItem todoItem = todoItemService.getTodoItemById(id)
      .orElseThrow(() -> new IllegalArgumentException("Todo Item with id " + id + " does not exist"));

    model.addAttribute(todoItem);
    return "todo-item";
  }



}
