package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosWithLimit {

  @Test
  public void sevenTodosMax() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] sevenTodos = db.filterWithLimits(allTodos, 7);
    assertEquals("Incorrect number of todos", 7, sevenTodos.length);
  }

  @Test
  public void twoTodosMax() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] twoTodos = db.filterWithLimits(allTodos, 2);
    assertEquals("Incorrect number of todos", 2, twoTodos.length);
  }

  @Test
  public void twentyTodosMax() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] twentyTodos = db.filterWithLimits(allTodos, 20);
    assertEquals("Incorrect number of todos", 20, twentyTodos.length);
  }
}
