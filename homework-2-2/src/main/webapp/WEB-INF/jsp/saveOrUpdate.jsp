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
<script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>
        function isNull( str ){
            if ( str == "" ) return true;
            var regu = "^[ ]+$";
            var re = new RegExp(regu);
            return re.test(str);
        }

        $(function () {

            $("#btn_save").bind("click",function () {
                // 发送ajax请求
                var id = $('#id').val().trim();
                if(isNull(id)){
                    id = null;
                }else{
                    id = parseInt(id);
                }
                var json = {"id": id, "name": $('#name').val().trim(),
                "address":$('#address').val().trim(), "phone":$('#phone').val().trim()}
                $.ajax({
                    url: '/saveOrUpdate',
                    type: 'POST',
                    data: JSON.stringify(json), //'{"id":"1","name":"李四"}',
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    success: function (data) {
                        location.href="/list"
                    }
                })
            })
        })
    </script>
</head>
<body>
<div>
    <h2>
        ﻿<c:if test="${obj==null}">
            添加
        </c:if>
        <c:if test="${obj!=null}">
            编辑
        </c:if>
    </h2>
    <fieldset>
        <form action="/saveOrUpdate" method="post">
            id：<input id="id" type="text" name="id" value="${obj.id}" disabled="disabled"><br>
            name：<input id="name" type="text" name="name" value="${obj.name}"><br>
            address: <input id="address" type="text" name="address" value="${obj.address}"><br>
            phone: <input id="phone" type="text" name="phone" value="${obj.phone}"><br>
            <input id="btn_save" class="sel_btn" type="button" value="保存">
        </form>
    </fieldset>
</div>
</body>
</html>