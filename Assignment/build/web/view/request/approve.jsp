<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Approve Leave Request</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
<div class="main">
    <div class="card gradient" style="max-width: 500px; margin: 60px auto; text-align: center;">
        <h2>✅ Approve Leave Request</h2>
        <p><b>Request ID:</b> ${param.id}</p>
        <p><b>Created by:</b> ${param.createdBy}</p>
        <p><b>Reason:</b> ${param.reason}</p>
        <p><b>From:</b> ${param.from}</p>
        <p><b>To:</b> ${param.to}</p>

        <form action="${pageContext.request.contextPath}/request/approve" method="POST">
            <input type="hidden" name="rid" value="${param.id}">
            <button type="submit" class="btn-green">Approve</button>
            <a href="${pageContext.request.contextPath}/request/list" class="btn-red">Cancel</a>
        </form>
    </div>
</div>
</body>
</html>
