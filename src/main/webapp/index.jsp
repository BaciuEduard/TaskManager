<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Manager</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 800px;
            width: 100%;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        #task-form {
            margin-bottom: 20px;
        }

        #task-form input, #task-form button {
            margin: 10px 0;
            padding: 10px;
            width: calc(100% - 22px);
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        #task-form button {
            background-color: #5cb85c;
            color: white;
            border: none;
            cursor: pointer;
        }

        #task-form button:hover {
            background-color: #4cae4c;
        }

        .task-section {
            margin-top: 20px;
        }

        .task-section h2 {
            color: #555;
            border-bottom: 2px solid #ddd;
            padding-bottom: 5px;
            margin-bottom: 15px;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        ul li {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
            background-color: #f7f7f7;
            text-align: left;
        }

        ul li.completed-task {
            background-color: #e8f5e9;
            border-color: #c8e6c9;
        }

        ul li div {
            margin: 5px 0;
        }

        ul li button {
            margin-left: 5px;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        ul li button:first-of-type {
            background-color: #0275d8;
            color: white;
        }

        ul li button:first-of-type:hover {
            background-color: #025aa5;
        }

        ul li button:last-of-type {
            background-color: #d9534f;
            color: white;
        }

        ul li button:last-of-type:hover {
            background-color: #c9302c;
        }

        .sort-button {
            background-color: #5bc0de;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }

        .sort-button:hover {
            background-color: #31b0d5;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Task Manager</h1>

    <div id="task-form">
        <form action="TaskServlet" method="post">
            <input type="text" id="name" name="name" placeholder="Task Name" required>
            <input type="text" id="description" name="description" placeholder="Description" required>
            <input type="text" id="priority" name="priority" placeholder="Priority" required>
            <input type="date" id="duedate" name="duedate" required>
            <button type="submit">Add Task</button>
        </form>
    </div>

    <div>
        <form action="TaskServlet" method="get">
            <input type="hidden" name="sort" value="name">
            <button type="submit" class="sort-button">Sort by Name</button>
        </form>
    </div>

    <div class="task-section">
        <h2>Pending Tasks</h2>
        <ul id="task-list">
            <c:forEach var="task" items="${tasks}">
                <c:if test="${!task.completed}">
                    <li>
                        <div><b>Name:</b> ${task.name}</div>
                        <div><b>Description:</b> ${task.description}</div>
                        <div><b>Priority:</b> ${task.priority}</div>
                        <form action="TaskServlet" method="post" style="display:inline;">
                            <input type="hidden" name="_method" value="UPDATE">
                            <input type="hidden" name="id" value="${task.id}">
                            <button type="submit">Mark as Completed</button>
                        </form>
                        <form action="TaskServlet" method="post" style="display:inline;">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="${task.id}">
                            <button type="submit">Delete</button>
                        </form>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <div class="task-section">
        <h2>Completed Tasks</h2>
        <ul id="completed-task-list">
            <c:forEach var="task" items="${tasks}">
                <c:if test="${task.completed}">
                    <li class="completed-task">
                        <div><b>Name:</b> ${task.name}</div>
                        <div><b>Description:</b> ${task.description}</div>
                        <div><b>Priority:</b> ${task.priority}</div>
                        <form action="TaskServlet" method="post" style="display:inline;">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="${task.id}">
                            <button type="submit">Delete</button>
                        </form>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
