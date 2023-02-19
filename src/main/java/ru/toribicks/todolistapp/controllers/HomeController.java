package ru.toribicks.todolistapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
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
}
