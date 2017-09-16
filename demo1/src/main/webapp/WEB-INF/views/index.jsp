<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>黄土地网站首页</title>
    <script type="text/javascript">
        function showInfo() {
            window.location.href = "${ctx}/login/desc";
        }
    </script>
</head>
<body>
<form action="${ctx}/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="提交"><input type="reset" value="重置">
                &nbsp;&nbsp; <a href="javascript:showInfo()">注</a>
                &nbsp;&nbsp; <a href="${ctx}/login/cacheInfo">Cache</a>
                &nbsp;&nbsp; <a href="${ctx}/login/destroyCache">CacheDestory</a>
                &nbsp;&nbsp; <a href="${ctx}/login/getUser">Spring&&Cache</a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="${ctx}/login/userRedis">UserRedis</a>
                <a href="${ctx}/login/userSpringRedis">UserSpringRedis</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
