<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<!-- 상대경로라서 action="/save"라고 / 를 안함. 하게 되면 localhost:8080/save라고 들어가짐 -->
<form action="save" method="post">
 username: <input type="text" name="username" />
 age: <input type="text" name="age" />
 <button type="submit">전송</button>
</form>
</body>
</html>

<!--// 이건 클라이언트에서 경로를 직접 호출하지 않게 해야하기 때문에 WEB-INF안에다가 넣으면 됌 -> rule-->