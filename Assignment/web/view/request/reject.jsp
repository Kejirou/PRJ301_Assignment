<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reject Leave Request</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
<div class="main">
    <div class="card gradient" style="max-width: 500px; margin: 60px auto; text-align: center;">
        <h2>❌ Reject Leave Request</h2>
        <p><b>Request ID:</b> ${param.id}</p>
        <p><b>Created by:</b> ${param.createdBy}</p>
        <p><b>Reason:</b> ${param.reason}</p>
        <p><b>From:</b> ${param.from}</p>
        <p><b>To:</b> ${param.to}</p>

        <form action="${pageContext.request.contextPath}/request/reject" method="POST">
            <input type="hidden" name="rid" value="${param.id}">
            <textarea name="comment" placeholder="Enter reject reason..." rows="4" required></textarea><br>
            <button type="submit" class="btn-red">Reject</button>
            <a href="${pageContext.request.contextPath}/request/list" class="btn-green">Cancel</a>
        </form>
    </div>
</div>
</body>
</html>
