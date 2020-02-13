<%-- 
    Document   : showPaymentOrder
    Created on : Feb 12, 2020, 4:22:17 PM
    Author     : LARISSA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Оплата выставленного счёта</title>
    </head>
    <body>
        <h1>Оплата счёта</h1>
        <p>${info}</p>
        <a href="index.jsp">Главная страница</a><br>
        <form action="returnOnBook" method="POST">
            Список выставленных счётов:<br>
            <c:if test="${listHistories == null}">
                <p>Нет выставленных счётов!</p>
            </c:if>
            <c:if test="${listHistories != null}">
                <select name="historyId"  size="3">
                    <c:forEach var="history" items="${listHistories}" varStatus="status">
                        <option value="${history.id}">
                            ${status.index+1}. ${history.firm.title} ${history.firm.address} читает книгу "${history.order.title}"
                        </option>
                    </c:forEach>
                </select>
            </c:if>
            <br>
            <input type="submit" value="Оплатить счёт">
        </form>
    </body>
</html>
