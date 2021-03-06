//code borrowed from users.js

/**
 * Function to get all the todos!
 */
function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByOwner() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?owner=" + document.getElementById("owner").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllBlancheTodos() {
  console.log("Getting all of the todos for owner: Blanche.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?owner=Blanche", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getlimitedTodos() {
  console.log("Getting limited number of todos");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?limit=" + document.getElementById("limit").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllGroceryTodos() {
  console.log("Getting all of the todos for category: grocery.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?category=groceries", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByCategory() {
  console.log("Getting all the todos by category.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?category=" + document.getElementById("category").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getTodosByBody() {
  console.log("Getting all the todos from the body.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?contains=" + document.getElementById("contains").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosById() {
  console.log("Getting all the todos by category.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?_id=" + document.getElementById("_id").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getOrderedTodos() {
  console.log("Getting all the todos in order by category");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?orderBy=" + document.getElementById("orderBy").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getStatusOfTodos() {

  if (document.getElementById("incomplete").checked) {
    console.log("Getting all incomplete statuses.");
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?status=incomplete", function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  } else if (document.getElementById("complete").checked)  {
    console.log("Getting all complete statuses.");
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?status=complete", function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  } else {

  }
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function (aUrl, aCallback) {
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function () {

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
