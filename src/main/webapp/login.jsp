<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
  String error = (String) request.getAttribute("error");
%>
<html>
<head>
  <title>로그인</title>
</head>
<body>
<h1>로그인</h1>

<% if (error != null) { %>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="<%=request.getContextPath()%>/login" method="post">
  <p>
    아이디: <input type="text" name="userId" required/>
  </p>
  <p>
    비밀번호: <input type="password" name="password" required/>
  </p>
  <p>
    <button type="submit">로그인</button>
  </p>
</form>

<p>
  <a href="<%=request.getContextPath()%>/signup.jsp">회원가입 하러 가기</a> |
  <a href="<%=request.getContextPath()%>/index.jsp">메인으로</a>
</p>

</body>
</html>
