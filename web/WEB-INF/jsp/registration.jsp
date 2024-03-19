<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <img src="${pageContext.request.contextPath}/images/users/42.jpeg" width="20%" height="20%" alt="User image">
        <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
            <label for="name">Name:
                <input type="text" name="name" id="name">
            </label><br>
            <label for="birthday">Birthday:
                <input type="date" name="birthday" id="birthday" required>
            </label><br>
            <label for="email">Email:
                <input type="text" name="email" id="email">
            </label><br>
            <label for="password">Password:
                <input type="password" name="password" id="password">
            </label><br>
            <label for="role">Role:
                <select name="role" id="role">
                    <c:forEach var="role" items="${requestScope.roles}">
                        <option value=${role}>${role}</option>
                    </c:forEach>
                </select>
            </label><br>
            <label>
                <c:forEach var="gender" items="${requestScope.genders}">
                    <input type="radio" name="gender" value=${gender}>${gender}
                </c:forEach><br>
            </label>
            <label for="imageId">Image:
                <input type="file" name="image" id="imageId">
            </label><br>
            <button type="submit">Send</button>
            <div>
                <c:if test="${not empty requestScope.errors}">
                    <div style="color: darkred">
                        <br><span>Errors:</span><br>
                        <c:forEach var="error" items="${requestScope.errors}">
                            <span>  - ${error.message}</span><br>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </form>
    </body>
</html>
