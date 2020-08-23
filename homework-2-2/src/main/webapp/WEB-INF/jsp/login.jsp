<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title>SpringMVC</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>

    </script>
<body>
    <h3>我是服务器：${pageContext.request.localPort}</h3>
    <h3>当前sessionId：${pageContext.session.id}</h3>
    <div>
        <h2>登录</h2>
        <fieldset>
            <span style="color: red">${msg}</span>
            <form action="${pageContext.request.contextPath}/login" method="post">
                用户名：<input type="text" name="username"><br>
                密码: <input type="password" name="password"><br>
                <input type="submit" value="登录">
            </form>
        </fieldset>
    </div>
</body>
</html>
