<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>黄土地网站首页</title>
</head>
<body>
<form action="login?" method="post">
    用户名：
    <input type="text" name="userName">
    <br>
    密码
    <input type="password" name="password">
    <br>
    <input type="submit" value="登陆">
    <input type="reset" value="重置">
</form>
</body>
</html>
