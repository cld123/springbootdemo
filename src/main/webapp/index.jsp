<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <%--    测试${pageContext.request.contextPath} 获取路径能不能使用

     --%>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>
<body>

<h1>那天夕阳下的奔跑，是我们无法忘记的青春!</h1>
<h1>那天夕阳下的奔跑，是我们无法忘记的青春!</h1>
<h1>那天夕阳下的奔跑，是我们无法忘记的青春!</h1>

<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

<a href="/file/download?fileName=登录密码.txt">登录密码.txt</a>
<script></script>


</body>
</html>