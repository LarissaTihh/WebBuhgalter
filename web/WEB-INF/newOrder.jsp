<%-- 
    Document   : newOrder
    Created on : Feb 12, 2020, 4:13:33 PM
    Author     : LARISSA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый счёт</title>
    </head>
    <body>
        <h1>Создать счёт</h1>
        <form action="addOrder" method="POST">
            Название счёта: <input type="text" name="title"><br><br>
            Кто выставил счёт: <input type="text" name="author"><br><br>
            Дата : <input type="text" name="year"><br><br>
            Сумма счёта: <input type="text" name="summ"><br><br>
            <input type="submit" value="Создать счёт"><br>
        </form>

    </body>
</html>
