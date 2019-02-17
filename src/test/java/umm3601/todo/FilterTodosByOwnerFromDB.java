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

    Todo[] age27Users = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of users with age 27", 43, age27Users.length);

    Todo[] age33Users = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals("Incorrect number of users with age 33", 61, age33Users.length);
  }

  @Test
  public void listUsersWithAgeFilter() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("owner", new String[]{"Blanche"});
    Todo[] age27Users = db.listTodos(queryParams);
    assertEquals("Incorrect number of users with age 27", 43, age27Users.length);

    queryParams.put("owner", new String[]{"Fry"});
    Todo[] age33Users = db.listTodos(queryParams);
    assertEquals("Incorrect number of users with age 33", 61, age33Users.length);
  }
}
