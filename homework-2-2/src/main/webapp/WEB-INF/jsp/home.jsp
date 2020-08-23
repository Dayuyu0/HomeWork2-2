<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SpringMVC</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>

    </script>
<body>
<div>
    <h2>HOME 页面</h2>
    <fieldset>
        <p>admin，tom 账号能访问的地址，跳转列表页面</p>
        <a href="/list">/list</a>
    </fieldset>
    <fieldset>
        <p>admin，jerry 账号能访问的地址，跳转单个查询页面</p>
        <a href="/findById?id=1">/findById?id=1</a>
    </fieldset>
    <fieldset>
        <a href="/logout">退出</a>
    </fieldset>
</div>
</body>
</html>
