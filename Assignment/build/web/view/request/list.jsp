<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Requests | FPT Leave</title>
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
        <li><a href="${pageContext.request.contextPath}/home">🏠 Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/request/list" class="active">📋 My Leave Requests</a></li>
        <li><a href="${pageContext.request.contextPath}/request/create">📝 Create Leave</a></li>
        <li><a href="${pageContext.request.contextPath}/division/agenda">📅 Division Agenda</a></li>
    </ul>
</div>

<!-- Main -->
<div class="main">
    <header class="topbar">
        <div class="page-title">Leave Request List</div>
        <div class="user-info">
            <span>${sessionScope.auth.displayname}</span>
            <form action="${pageContext.request.contextPath}/Logout" method="POST" style="display:inline;">
                <button class="logout-btn">Logout</button>
            </form>
        </div>
    </header>

    <div class="content">
        <!-- Info Card -->
        <div class="card info">
            <h3>📄 Leave Requests</h3>
            <p>View and manage your leave requests below.</p>
        </div>

        <!-- Gradient Section Title -->
        <div class="card gradient" style="grid-column: span 2;">
            <h3>📋 My Leave Requests</h3>
            <p>All leave requests you or your subordinates have submitted.</p>
        </div>

        <!-- Table Card (main list) -->
        <div class="card table-card" style="grid-column: span 2;">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Created By</th>
                        <th>Status</th>
                        <th>Processed By</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${requests}">
                        <tr>
                            <td>${r.reason}</td>
                            <td>${r.from}</td>
                            <td>${r.to}</td>
                            <td>${r.created_by.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${r.status == 1}">
                                        <span class="badge yellow">In Progress</span>
                                    </c:when>
                                    <c:when test="${r.status == 2}">
                                        <span class="badge green">Approved</span>
                                    </c:when>
                                    <c:when test="${r.status == 3}">
                                        <span class="badge red">Rejected</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:out value="${r.processed_by != null ? r.processed_by.name : '-'}" />
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/request/approve" method="post" style="display:inline;">
                                 <input type="hidden" name="rid" value="${r.id}">
                                   <button class="approve-btn">Approve</button>
                              </form>
                                 <form action="${pageContext.request.contextPath}/request/reject" method="post" style="display:inline;">
                                    <input type="hidden" name="rid" value="${r.id}">
                                 <button class="reject-btn">Reject</button>
                              </form>

                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty requests}">
                        <tr>
                            <td colspan="7" style="text-align:center; padding:20px; color:#666;">
                                No leave requests found.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
