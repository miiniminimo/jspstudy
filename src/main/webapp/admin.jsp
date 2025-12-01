<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.study.jspstudy.model.User" %>

<%
  @SuppressWarnings("unchecked")
  List<User> users = (List<User>) request.getAttribute("users");
  if (users == null) {
    users = java.util.Collections.emptyList();
  }
%>

<html>
<head>
  <title>관리자 페이지 - 회원관리</title>
</head>
<body>
<h1>관리자 페이지 - 회원관리</h1>

<p>
  <a href="<%=request.getContextPath()%>/home.jsp">메인으로</a> |
  <a href="<%=request.getContextPath()%>/logout">로그아웃</a>
</p>

<table border="1" cellpadding="5" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>아이디</th>
    <th>이름</th>
    <th>학교</th>
    <th>학년</th>
    <th>이메일</th>
    <th>관리자 여부</th>
    <th>가입일</th>
    <th>관리</th>
  </tr>

  <% for (User u : users) { %>
  <tr>
    <td><%= u.getId() %></td>
    <td><%= u.getUserId() %></td>
    <td><%= u.getName() %></td>
    <td><%= u.getSchoolName() %></td>
    <td><%= u.getGrade() %></td>
    <td><%= u.getEmail() %></td>
    <td><%= u.isAdmin() ? "관리자" : "일반" %></td>
    <td><%= u.getCreatedAt() %></td>
    <td>
      <!-- 삭제 -->
      <form action="<%=request.getContextPath()%>/admin"
            method="post"
            style="display:inline;">
        <input type="hidden" name="id" value="<%= u.getId() %>"/>
        <input type="hidden" name="action" value="delete"/>
        <button type="submit">삭제</button>
      </form>

      <!-- 관리자 권한 토글 -->
      <% if (u.isAdmin()) { %>
      <form action="<%=request.getContextPath()%>/admin"
            method="post"
            style="display:inline;">
        <input type="hidden" name="id" value="<%= u.getId() %>"/>
        <input type="hidden" name="action" value="revokeAdmin"/>
        <button type="submit">관리자 해제</button>
      </form>
      <% } else { %>
      <form action="<%=request.getContextPath()%>/admin"
            method="post"
            style="display:inline;">
        <input type="hidden" name="id" value="<%= u.getId() %>"/>
        <input type="hidden" name="action" value="grantAdmin"/>
        <button type="submit">관리자로 지정</button>
      </form>
      <% } %>
    </td>
  </tr>
  <% } %>
</table>

</body>
</html>
