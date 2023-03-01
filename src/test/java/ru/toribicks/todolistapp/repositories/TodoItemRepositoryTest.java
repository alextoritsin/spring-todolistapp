package ru.toribicks.todolistapp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.toribicks.todolistapp.models.TodoItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TodoItemRepositoryTest {
  @Autowired
  private TodoItemRepository todoItemRepository;

  @Autowired
  private TestEntityManager entityManager;

  @BeforeEach
  public void setUp() {
    TodoItem todoItem1 = new TodoItem("conquer the world");
    TodoItem todoItem2 = new TodoItem("feed the cat");
    entityManager.persist(todoItem1);
    entityManager.persist(todoItem2);
    entityManager.flush();
  }

  @Test
  public void EntitiesCount() {
    assertThat(todoItemRepository.findAll().size()).isEqualTo(2);
  }

  @Test
  @DisplayName("ID not changing after changing description")
  public void IdStaysTheSameDescription() {
    List<TodoItem> todoItems = todoItemRepository.findAll();
    TodoItem todoItem = todoItems.get(0);
    todoItem.setDescription("do not conquer the world");
    todoItemRepository.save(todoItem);

    assertThat(todoItemRepository.findAll().size()).isEqualTo(2);
  }

  @Test
  @DisplayName("ID not changing after changing isCompleted")
  public void IdStaysTheSameCompleted() {
    List<TodoItem> todoItems = todoItemRepository.findAll();
    todoItems.get(0).setIsComplete(true);
    todoItemRepository.save(todoItems.get(0));

    assertThat(todoItemRepository.findAll().size()).isEqualTo(2);
  }

  @Test
  public void countChangingAfterDeleting() {
    List<TodoItem> todoItems = todoItemRepository.findAll();
    todoItemRepository.delete(todoItems.get(0));

    assertThat(todoItemRepository.findAll().size()).isEqualTo(1);
  }

}
