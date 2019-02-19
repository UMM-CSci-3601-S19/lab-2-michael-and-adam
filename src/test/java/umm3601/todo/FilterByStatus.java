package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class FilterByStatus {

  @Test
  public void filterCompletes() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] complete = db.filterTodosByStatusComplete(allTodos);
    assertEquals("Incorrect number of completes", 143, complete.length);
  }

  @Test
  public void filterIncompletes() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] incomplete = db.filterTodosByStatusIncomplete(allTodos);
    assertEquals("Incorrect number of completes", 157, incomplete.length);
  }

}

