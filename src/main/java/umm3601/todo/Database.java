//code borrowed from user's Database.java
package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of todo info
 * <p>
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of todo data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `TodoController` to "query" the "database".
 */
public class Database {

  private Todo[] allTodos;

  public Database(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single todo specified by the given ID. Return
   * `null` if there is no todo with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    if (queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];

      if (targetStatus.equals("incomplete")){
        filteredTodos = filterTodosByStatusIncomplete(filteredTodos);
      } else if (targetStatus.equals("complete")) {
        filteredTodos = filterTodosByStatusComplete(filteredTodos);
      }
    }

    if (queryParams.containsKey("contains")) {
      String targetBody = queryParams.get("contains")[0];
      filteredTodos = filterTodosByBody(filteredTodos, targetBody);
    }

    if (queryParams.containsKey("_id")) {
      String targetId = queryParams.get("_id")[0];
      filteredTodos = filterTodosById(filteredTodos, targetId);
    }

//  In our attempt to support an arbitrary combination of search parameters, we ran into considerable trouble with any
//    search that used limit. The returned amount of items was at MAX the limit, but often less were returned. This was
//    solved by reordering the if statements, especially putting limit at the end. Owner + Limit worked as intended, the
//    way we originally had the ifs ordered, because owner was the first if statement before limit, but all the other
//    if statements came after the limit if statement. When making the limit if statement last, the number of returned
//    todos was correct, as the limiting number was applied only after all relevant fields were found.
    if (queryParams.containsKey("limit")) {
      int targetLimit= Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = filterWithLimits(filteredTodos, targetLimit);
    }

//    We attempted to set up orderBy. There are calls to sortBy different methods below too. We commented it all out
//    because we ran out of time to properly implement the ordering methods, but we wanted to demonstrate that we
//    put some work into it.

//    if(queryParams.containsKey("orderBy")) {
//      String targetOrder = queryParams.get("orderBy")[0];
//      System.out.println(targetOrder);
//      if (targetOrder.equals("body")) {
//        filteredTodos = sortByBody(filteredTodos, targetOrder);
//      } else if (targetOrder.equals("status")) {
//        filteredTodos = sortByStatus(filteredTodos, targetOrder);
//      } else if (targetOrder.equals("category")) {
//        filteredTodos = sortByCategory(filteredTodos, targetOrder);
//      } else if (targetOrder.equals("owner")) {
//        filteredTodos = sortByOwner(filteredTodos, targetOrder);
//      }
//    }

    return filteredTodos;
  }

  /**
   * Get an array of all the todos having the target age.
   *
   * @param todos     the list of todos to filter by age
   * @param targetOwner the target age to look for
   * @return an array of all the todos from the given list that have
   * the target age
   */
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterWithLimits(Todo[] todos, int limitNum) {
    return Arrays.stream(todos).limit(limitNum).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatusComplete(Todo[] todos) {
    return Arrays.stream(todos).filter(x -> x.status == true).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatusIncomplete(Todo[] todos) {
    return Arrays.stream(todos).filter(x -> x.status == false).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetBody) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }

//=============================================================================================

//  public Todo[] sortByBody(Todo[] todos, String targetOrder) {
//    return Arrays.stream(todos)
//      .sorted(Comparator.comparing(a -> x.body))
//      .toArray(Todo[]::new);
//  }
//
//  public Todo[] sortByStatus(Todo[] todos, String targetOrder) {
//    return Arrays.stream(todos)
//      .sorted(Comparator.comparing(a -> x.body))
//      .toArray(Todo[]::new);
//  }
//
//  public Todo[] sortByCategory(Todo[] todos, String targetOrder) {
//    return Arrays.stream(todos)
//      .sorted(Comparator.comparing(a -> x.body))
//      .toArray(Todo[]::new);
//  }
//
//  public Todo[] sortByOwner(Todo[] todos, String targetOrder) {
//    return Arrays.stream(todos)
//      .sorted(Comparator.comparing(a -> x.body))
//      .toArray(Todo[]::new);
//  }

//=============================================================================================
  public Todo[] filterTodosById(Todo[] todos, String targetId) {
    return Arrays.stream(todos).filter(x -> x._id.equals(targetId)).toArray(Todo[]::new);
  }
}
