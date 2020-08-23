<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<style>
    .sel_btn{
        height: 21px;
        line-height: 21px;
        padding: 0 11px;
        background: #02bafa;
        border: 1px #26bbdb solid;
        border-radius: 3px;
        /*color: #fff;*/
        display: inline-block;
        text-decoration: none;
        font-size: 12px;
        outline: none;
    }
    .ch_cls{
        background: #e4e4e4;
    }
</style>
</head>
<body>
    <div>
        <a class="sel_btn" href="/saveOrUpdate">新增</a>
    </div>
    <table width="80%" border="1" align="center">
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>地址</th>
        <th>电话</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="e">
        <tr>
            <td>${e.id}</td>
            <td>${e.name}</td>
            <td>${e.address}</td>
            <td>${e.phone}</td>
            <td>
                <a class="sel_btn ch_cls" href="/deleteById?id=${e.id}">删除</a>
                <a class="sel_btn ch_cls" href="/saveOrUpdate?id=${e.id}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>