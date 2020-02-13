<%-- 
    Document   : listOrders
    Created on : Feb 12, 2020, 3:10:42 PM
    Author     : LARISSA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <title>Список счетов</title>
    </head>
    <body>
        <h1>Список наших счетов</h1>
        <ol>
            <c:forEach var="order" items="${listOrders}">
                <li>${order.title}. ${order.author}. ${order.year}. ${order.summ}</li>  
            </c:forEach>
             </ol>
    </body>
</html>
