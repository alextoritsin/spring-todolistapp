package ru.toribicks.todolistapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.toribicks.todolistapp.controllers.TodoItemController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

  @Autowired
  private TodoItemController todoItemController;

  @Test
  public void contextLoads() throws Exception {
    assertThat(todoItemController).isNotNull();
  }
}
