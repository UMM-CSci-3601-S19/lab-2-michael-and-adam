package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class FilterTodosByBody {

  @Test
  public void FilterStringOne() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] stringOne = db.filterTodosByBody(allTodos, "esse");
    assertEquals("Incorrect number bodies containing the desired string in todos", 70, stringOne.length);
  }

  @Test
  public void filterStingTwo() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] stringTwo = db.filterTodosByBody(allTodos, "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.");
    assertEquals("Incorrect number bodies containing the desired string in todos", 1, stringTwo.length);
  }

  @Test
  public void filterStingThree() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] stringThree = db.filterTodosByBody(allTodos, "e");
    assertEquals("Incorrect bodies containing the desired string in todos", 300, stringThree.length);
  }

  @Test
  public void filterNoString() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] noString = db.filterTodosByBody(allTodos, "asdfasdfasdfasfd");
    assertEquals("Incorrect bodies containing the desired string in todos", 0, noString.length);
  }


}

