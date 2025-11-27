<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.study.jspstudy.model.User" %>
<%
  User user = (User) request.getAttribute("user");
  String msg = (String) request.getAttribute("msg");
%>
<html>
<head>
  <title>마이페이지 - 정보수정</title>
</head>
<body>
<h1>마이페이지 - 정보수정</h1>

<% if (msg != null) { %>
<p style="color:blue;"><%= msg %></p>
<% } %>

<form action="<%=request.getContextPath()%>/mypage" method="post">
  <p>
    아이디: <strong><%= user.getUserId() %></strong>
    (아이디는 수정할 수 없습니다)
  </p>
  <p>
    비밀번호:
    <input type="password" name="password"
           value="<%= user.getPassword() %>" required/>
  </p>
  <p>
    이름:
    <input type="text" name="name"
           value="<%= user.getName() %>" required/>
  </p>
  <p>
    학교:
    <input type="text" name="schoolName"
           value="<%= user.getSchoolName() != null ? user.getSchoolName() : "" %>"/>
  </p>
  <p>
    학년/직군:
    <input type="text" name="grade"
           value="<%= user.getGrade() != null ? user.getGrade() : "" %>"/>
  </p>
  <p>
    이메일:
    <input type="email" name="email"
           value="<%= user.getEmail() != null ? user.getEmail() : "" %>"/>
  </p>

  <p>
    <button type="submit">정보 수정하기</button>
  </p>
</form>

<p>
  <a href="<%=request.getContextPath()%>/index.jsp">메인으로</a> |
  <a href="<%=request.getContextPath()%>/logout">로그아웃</a>
</p>

</body>
</html>
