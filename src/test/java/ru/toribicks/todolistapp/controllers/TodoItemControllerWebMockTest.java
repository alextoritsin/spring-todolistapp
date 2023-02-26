package ru.toribicks.todolistapp.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
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

  @Test
  @DisplayName("Shows create new item view")
  public void createNewShouldReturnAddView() throws Exception {
    this.mockMvc.perform(get("/todoitems/new"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("Add a new")));
  }

  @Test
  public void postRequestReturnRedirectToIndex() throws Exception {
    this.mockMvc.perform(post("/todoitems")
      .param("description", "get hired by hh.ru"))
      .andDo(print())
      .andExpect(status().is3xxRedirection());
  }

  @Test
  public void badPostRequestShouldReturnValidationError() throws Exception {
    this.mockMvc.perform(post("/todoitems")
      .param("description", ""))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(model().attributeHasErrors("todoItem"))
      .andExpect(model().errorCount(2));

    this.mockMvc.perform(post("/todoitems")
      .param("description", "123"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(model().attributeHasErrors("todoItem"))
      .andExpect(model().errorCount(1));
  }
}