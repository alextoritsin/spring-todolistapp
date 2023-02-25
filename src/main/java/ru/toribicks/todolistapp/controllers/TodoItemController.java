package ru.toribicks.todolistapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.toribicks.todolistapp.models.TodoItem;
import ru.toribicks.todolistapp.services.TodoItemService;

@Controller
public class TodoItemController {
  @Autowired
  private TodoItemService todoItemService;

  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("todoItems", todoItemService.getAllTodoItems());
    return modelAndView;
  }

  @GetMapping("/todoitems/new")
  public String showCreateForm(Model model) {
    TodoItem todoItem = new TodoItem();
    model.addAttribute(todoItem);
    return "todo-item";
  }

  @PostMapping("/todoitems")
  public String createTodoItem(@ModelAttribute("todoItem") @Valid TodoItem todoItem,
                                BindingResult bindingResult) {
    if (bindingResult.hasErrors()) return "todo-item";

    todoItemService.saveTodoItem(todoItem);
    return "redirect:/";
  }

  @DeleteMapping("/delete/{id}")
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
