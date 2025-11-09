<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Leave Request System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
    <div class="login-container">
        <img src="${pageContext.request.contextPath}/images/FPT_logo_2010.svg.png" alt="FPT Logo" class="logo">
        <h2>Login to Your Account</h2>

        <form action="Login" method="POST" class="login-form">
            <label for="txtUsername">Username:</label>
            <input type="text" name="username" id="txtUsername" placeholder="Enter username" required>

            <label for="txtPassword">Password:</label>
            <input type="password" name="password" id="txtPassword" placeholder="Enter password" required>

            <button type="submit" id="btnLogin">Login</button>

            <p class="error">${requestScope.error}</p>
        </form>
    </div>
</body>
</html>
