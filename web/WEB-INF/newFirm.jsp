<%-- 
    Document   : newFirm
    Created on : Feb 12, 2020, 4:17:09 PM
    Author     : LARISSA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новая фирма</title>
    </head>
    <body>
        <h1>Зарегистрироваться</h1>
        <form action="addFirm" method="POST">
            Имя : <input type="text" name="title"><br><br>
            Адрес  : <input type="text" name="address"><br><br>
            email: <input type="text" name="email"><br><br>  
            Логин : <input type="text" name="login"><br><br>
            Пароль : <input type="password" name="password"><br><br>

            <input type="submit" value="Создать фирму"><br>
        </form>
    </body>
</html>
