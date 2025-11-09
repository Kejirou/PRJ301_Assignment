<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>System Message | Leave Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>

    <div class="message-container">
        <h2>System Message</h2>
        <div class="message-text">
            ${requestScope.message}
        </div>
        <a href="${pageContext.request.contextPath}/Login" class="back-link">← Back to Login</a>
    </div>

</body>
</html>
