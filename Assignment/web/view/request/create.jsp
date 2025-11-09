<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo đơn xin nghỉ phép | FPT Leave</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        .form-container {
            max-width: 550px;
            margin: 60px auto;
            background: #fff;
            padding: 40px 50px;
            border-radius: 16px;
            box-shadow: 0 6px 16px rgba(0,0,0,0.15);
            animation: fadeIn 0.4s ease;
        }

        .form-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #444;
        }

        input[type="date"], textarea {
            width: 100%;
            padding: 10px 12px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 15px;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        .submit-btn {
            background: linear-gradient(135deg, #6a8dff, #8a5fff);
            color: #fff;
            font-weight: bold;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            transition: 0.3s;
        }

        .submit-btn:hover {
            opacity: 0.9;
        }

        .user-info {
            font-size: 14px;
            margin-bottom: 20px;
            color: #555;
            background: #f5f6ff;
            padding: 10px 15px;
            border-radius: 8px;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
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
        <li><a href="${pageContext.request.contextPath}/request/create" class="active">📝 Create Leave</a></li>
        <li><a href="${pageContext.request.contextPath}/division/agenda">📅 Division Agenda</a></li>
        <li><a href="#">👤 Profile</a></li>
    </ul>
</div>

<div class="main">
    <header class="topbar">
        <div class="page-title">Tạo đơn xin nghỉ phép</div>
        <div class="user-info">
            <span>${sessionScope.auth.displayname}</span>
            <a href="${pageContext.request.contextPath}/Logout" class="logout-btn">Logout</a>
        </div>
    </header>

    <div class="content">
        <div class="form-container">
            <h2>Đơn xin nghỉ phép</h2>

            <div class="user-info">
                User: <b>${sessionScope.auth.displayname}</b>,
                Role: Nhân viên, Dept: IT
            </div>

            <form action="${pageContext.request.contextPath}/request/create" method="POST">
                <div class="form-group">
                    <label for="from">Từ ngày:</label>
                    <input type="date" id="from" name="from" required>
                </div>

                <div class="form-group">
                    <label for="to">Tới ngày:</label>
                    <input type="date" id="to" name="to" required>
                </div>

                <div class="form-group">
                    <label for="reason">Lý do:</label>
                    <textarea id="reason" name="reason" placeholder="Nhập lý do nghỉ phép..." required></textarea>
                </div>

                <button type="submit" class="submit-btn">Gửi</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
