<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link rel="stylesheet" href="./assets/styles/style.css">
    </head>
    <body>

        <!--Add A User-->
        <div>
            <h2>Add User</h2>
            <form action="users" method="POST">
                <input type="email" name="email" placeholder="Email" required><br>
                <input type="text" name="firstname" placeholder="First Name" required><br>
                <input type="text" name="lastname" placeholder="Last Name" required><br>
                <input type="radio" name="activity" value="0" checked>Active<br>
                <input type="radio" name="activity" value="1">Inactive<br>
                <select name="roleId">
                    <c:forEach items="${roles}" var="roles">
                        <option value="${role.roleId}">${role.rolename}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Add User">
                <input type="hidden" name="action" value="add">
            </form>
        </div>

        <!--View All Users-->
        <div>
            <h2>View Users</h2>

            <table>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Active</th>
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>

                <c:forEach var="userItem" items="${users}">
                    <tr>
                        <input type="radio" name="users" value="${userItem}">
                        <td>${userItem.email}</td>
                        <td>${userItem.firstname}</td>
                        <td>${userItem.lastname}</td>
                        <td>${userItem.active}</td>
                        <td>${userItem.role.rolename}</td>
                        <td>Edit</td>
                        <td>
                            <input type="hidden" name="action" value="delete">
                            <c:if test="${users.size() > 0}">
                                <input type="submit" value="Delete User">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                    
            </table>
        </div>

    </body>
</html>