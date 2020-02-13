<%-- 
    Document   : listFirms
    Created on : Feb 12, 2020, 3:15:02 PM
    Author     : LARISSA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список фирм</title>
    </head>
    <body>
        <h1>Список наших фирм</h1>
        <ol>
            <c:forEach var="firm" items="${listFirms}">
                 <li>${firm.title}. ${firm.address}. ${firm.email}</li>
            </c:forEach>
       
        </ol>
    </body>
</html>