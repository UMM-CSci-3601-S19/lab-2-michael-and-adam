package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

public class FilterTodosWithLimit {

  @Test
  public void sevenTodosMax() throws IOException {
    Database db = new Database("src/main/data/todos.json");
  }

  @Test
  public void threeTodosMax() throws IOException {
    Database db = new Database("src/main/data/todos.json");
  }
}
