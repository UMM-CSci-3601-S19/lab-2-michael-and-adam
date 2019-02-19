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
public class FilterByCategory {

  @Test
  public void filterVideoGames() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] videoGames = db.filterTodosByCategory(allTodos, "video games");
    assertEquals("Incorrect number of video games todos", 71, videoGames.length);
  }

  @Test
  public void filterSoftwareDesign() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] softwareDesign = db.filterTodosByCategory(allTodos, "software design");
    assertEquals("Incorrect number of software design todos", 74, softwareDesign.length);
  }

  @Test
  public void filterHomework() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] homework = db.filterTodosByCategory(allTodos, "homework");
    assertEquals("Incorrect number of homework todos", 79, homework.length);
  }

  @Test
  public void filterGroceries() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] groceries = db.filterTodosByCategory(allTodos, "groceries");
    assertEquals("Incorrect number of groceries todos", 76, groceries.length);
  }

  @Test
  public void filterMonsterHouse() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] monsterHouse = db.filterTodosByCategory(allTodos, "monster house");
    assertEquals("Incorrect number of monster house", 0, monsterHouse.length);
  }

}

