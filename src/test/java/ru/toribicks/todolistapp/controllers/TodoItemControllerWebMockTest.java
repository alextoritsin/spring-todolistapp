package ru.toribicks.todolistapp.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.toribicks.todolistapp.services.TodoItemService;

@WebMvcTest(TodoItemController.class)
class TodoItemControllerWebMockTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoItemService todoItemService;

  @Test
  public void indexShouldReturnAppName() throws Exception {
    this.mockMvc.perform(get("/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect((content().string(containsString("Todo List Application"))))
      .andExpect((content().string(containsString("Welcome!"))));
  }
}