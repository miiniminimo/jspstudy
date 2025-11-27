<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
  String error = (String) request.getAttribute("error");
%>
<html>
<head>
  <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>

<% if (error != null) { %>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="<%=request.getContextPath()%>/signup" method="post">
  <p>
    아이디: <input type="text" name="userId" required/>
  </p>
  <p>
    비밀번호: <input type="password" name="password" required/>
  </p>
  <p>
    이름: <input type="text" name="name" required/>
  </p>
  <p>
    학교명: <input type="text" name="schoolName"/>
  </p>
  <p>
    학년/직군: <input type="text" name="grade"/>
  </p>
  <p>
    이메일: <input type="email" name="email"/>
  </p>
  <p>
    <button type="submit">회원가입</button>
  </p>
</form>

<p>
  <a href="<%=request.getContextPath()%>/login.jsp">로그인 하러 가기</a> |
  <a href="<%=request.getContextPath()%>/index.jsp">메인으로</a>
</p>

</body>
</html>
