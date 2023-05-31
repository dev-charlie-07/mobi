<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
</head>
<body>
    <h1>Reset Password</h1>
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/resetPassword" method="post">
        <input type="hidden" name="email" value="${email}">
        <input type="hidden" name="token" value="${token}">
        <label for="password">New Password:</label>
        <input type="password" id="password" name="password" required>
        <button type="submit">Reset Password</button>
    </form>
</body>
</html>
