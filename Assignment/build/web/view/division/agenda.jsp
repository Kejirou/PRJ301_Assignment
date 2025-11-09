<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Division Agenda | FPT Leave</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        .agenda-wrapper {
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .agenda-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
        }

        .agenda-table th, .agenda-table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        .agenda-table th {
            background-color: #4b2cc3;
            color: white;
        }

        .present {
            background-color: #b6f5c4; /* xanh lá nhạt */
        }

        .absent {
            background-color: #ffb3b3; /* đỏ nhạt */
        }

        .legend {
            margin-top: 15px;
            font-size: 0.9em;
        }

        .legend span {
            display: inline-block;
            padding: 4px 10px;
            border-radius: 4px;
            margin-right: 10px;
        }

        .legend .present-sample { background: #b6f5c4; }
        .legend .absent-sample { background: #ffb3b3; }
    </style>
</head>
<body>

<div class="sidebar">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/images/FPT_logo_2010.svg.png" alt="FPT Logo">
        <h2>FPT Leave</h2>
    </div>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/home">🏠 Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/request/list">📋 My Leave Requests</a></li>
        <li><a href="${pageContext.request.contextPath}/request/create">📝 Create Leave</a></li>
        <li><a href="${pageContext.request.contextPath}/division/agenda" class="active">📅 Division Agenda</a></li>
    </ul>
</div>

<div class="main">
    <header class="topbar">
        <div class="page-title">📅 Division Agenda</div>
        <div class="user-info">
            <span>${sessionScope.auth.displayname}</span>
            <form action="${pageContext.request.contextPath}/Logout" method="POST" style="display:inline;">
                <button class="logout-btn">Logout</button>
            </form>
        </div>
    </header>

    <div class="content">
        <div class="agenda-wrapper">
            <h3>Team Leave Overview</h3>
            <p>From <strong>${from}</strong> to <strong>${to}</strong></p>

            <table class="agenda-table">
                <thead>
                    <tr>
                        <th>Nhân sự</th>
                        <c:forEach var="d" items="${days}">
    <c:set var="dateCheck" value="${java.sql.Date.valueOf(d)}" />
    <c:choose>
        <c:when test="${dateCheck >= req.from && dateCheck <= req.to}">
            <td class="absent"></td>
        </c:when>
        <c:otherwise>
            <td class="present"></td>
        </c:otherwise>
    </c:choose>
</c:forEach>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="req" items="${agenda}">
                        <tr>
                            <td>${req.created_by.name}</td>
                            <c:forEach var="i" begin="0" end="6">
                                <c:set var="dateCheck" value="${from + i}" />
                                <c:choose>
                                    <c:when test="${dateCheck >= req.from && dateCheck <= req.to}">
                                        <td class="absent"></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="present"></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="legend">
                <span class="present-sample">Có đi làm</span>
                <span class="absent-sample">Nghỉ phép</span>
            </div>
        </div>
    </div>
</div>

</body>
</html>
