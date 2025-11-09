<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FPT Leave Management Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/images/FPT_logo_2010.svg.png" alt="FPT Logo">
        <h2>FPT Leave</h2>
    </div>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/home" class="active">🏠 Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/request/list">📋 My Leave Requests</a></li>
        <li><a href="${pageContext.request.contextPath}/request/create">📝 Create Leave</a></li>
        <li><a href="${pageContext.request.contextPath}/request/agenda">📅 Division Agenda</a></li>

    </ul>
</div>

<!-- Main Content -->
<div class="main">
    <header class="topbar">
        <div class="page-title">Dashboard</div>
        <div class="user-info">
            <span>${sessionScope.auth.displayname}</span>
            <a href="${pageContext.request.contextPath}/Logout" class="logout-btn">Logout</a>
        </div>
    </header>

    <div class="content">
        <div class="card info">
            <h3>👋 Welcome, ${sessionScope.auth.displayname}</h3>
            <p>Employee ID: <strong>${sessionScope.auth.employee.id}</strong></p>
            <p>Employee Name: <strong>${sessionScope.auth.employee.name}</strong></p>
        </div>

        <div class="card gradient">
            <h3>📅 Leave Summary</h3>
            <p>Track your leave requests and approvals here.</p>
        </div>

        <div class="card gradient">
            <h3>🧭 Quick Actions</h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/request/list">View Requests</a></li>
                <li><a href="${pageContext.request.contextPath}/request/create">New Request</a></li>
                <li><a href="${pageContext.request.contextPath}/division/agenda">Division Agenda</a></li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
