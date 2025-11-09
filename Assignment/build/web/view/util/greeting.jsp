<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="greeting-box" style="padding: 10px; background: #f9f9f9; border-radius: 8px; margin-bottom: 10px;">
    <c:if test="${sessionScope.auth ne null}">
        <p>
            👋 Welcome, <strong>${sessionScope.auth.displayname}</strong><br>
            Employee: ${sessionScope.auth.employee.id} - ${sessionScope.auth.employee.name}
        </p>
        <!-- ✅ Logout link luôn đúng -->
        <a href="${pageContext.request.contextPath}/logout" class="logout-link"
           style="color: #fff; background: #007bff; padding: 6px 12px; border-radius: 5px; text-decoration: none;">
            🚪 Logout
        </a>
    </c:if>

    <c:if test="${sessionScope.auth eq null}">
        <p>
            You are not logged in yet!
            Please <a href="${pageContext.request.contextPath}/login"
                      style="color: #007bff; text-decoration: none;">login</a>.
        </p>
    </c:if>
</div>
