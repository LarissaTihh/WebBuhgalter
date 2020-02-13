<%-- 
    Document   : showTakeOnOrder
    Created on : Feb 12, 2020, 4:50:19 PM
    Author     : LARISSA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поиск счёта</title>

    </head>
    <body>
        <p>${info}</p>
        <a href="index.jsp">Главная страница</a><br>
        <form action="takeOnOrder" method="POST">
            <h2>Список счетов</h2> 
            <select name="orderId">
                <c:forEach var="order" items="${listOrders}" varStatus="status">
                    <option value="${order.id}">
                        ${status.index+1}) ${order.title}. ${order.author}. ${order.year}
                    </option>                          
                </c:forEach>
            </select>
            <h2>Список фирм</h2>
             <select name="firmId">
                <c:forEach var="firm" items="${listFirmss}" varStatus="status">
                    <option value="${firm.id}">
                        ${status.index+1}) ${firm.title}. ${firm.address}. ${firm.email}
                        
                    </option>                          
                </c:forEach>
            </select>
            <input type="submit" value="Выбрать счёт">
        </form>
    </body>
</html>

