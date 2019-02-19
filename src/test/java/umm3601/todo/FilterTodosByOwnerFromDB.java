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
public class FilterTodosByOwnerFromDB {

  @Test
  public void filterTodosWithOwner() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownerBlanche = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of owner Blanche todos", 43, ownerBlanche.length);

    Todo[] ownerFry = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals("Incorrect number of owner Fry todos", 61, ownerFry.length);
  }


  @Test
  public void filterNonExistentOwner() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] nonOwner = db.filterTodosByOwner(allTodos, "ZIPITYDOODAHBORRISDOTCOM");
    assertEquals("Incorrect number of fake owners", 0, nonOwner.length);
  }
}
