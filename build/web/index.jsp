<%-- 
    Document   : index
    Created on : Feb 11, 2020, 6:12:50 PM
    Author     : LARISSA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Наша бухгалтерия</title>
    </head>
    <body>
        <H1>Добро пожаловать в Perfect Pluss</H1>
        <p>${info}</p><br>
        <p>Изучаем как работает вебприложение на Java</p>
        <a href="showLogin">Вход</a><br>
        <a href="logout">Выход</a><br>
        <a href="newOrder">Создать счёт</a><br>
        <a href="listOrders">Список счетов</a><br>
        <a href="newFirm">Создать фирму</a><br>
        <a href="listFirms">Список фирм</a><br>
        <a href="showTakeOnOrder">Выставить счёт</a><br>
        <a href="showPaymentOrder">Оплатить счёт</a><br>
    </body>
</html>
