package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterById {

  @Test
  public void filterFirstId() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] idOne = db.filterTodosById(allTodos, "58895985a22c04e761776d54");
    assertEquals("Incorrect number of ids for todos", 1, idOne.length);
  }

  @Test
  public void filterSecondId() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] idTwo = db.filterTodosById(allTodos, "5889598555fbbad472586a56");
    assertEquals("Incorrect number of ids todos", 1, idTwo.length);
  }

  @Test
  public void filterThirdId() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] idThree = db.filterTodosById(allTodos, "5555555555555");
    assertEquals("Incorrect number of ids todos", 0, idThree.length);
  }
}
