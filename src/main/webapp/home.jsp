<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.study.jspstudy.model.User" %>
<%
    User loginUser = (User) session.getAttribute("loginUser");
%>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
<h1>조별 프로젝트 메인 페이지 (index.jsp)</h1>

<% if (loginUser == null) { %>
<!-- ✅ 로그인 안 한 상태: [회원 가입], [회원 로그인] 만 보이기 -->
<p>현재 로그인 되어 있지 않습니다.</p>

<ul>
    <li>
        <!-- [회원 가입] -->
        <a href="<%=request.getContextPath()%>/signup.jsp">[회원 가입]</a>
    </li>
    <li>
        <!-- [회원 로그인] -->
        <a href="<%=request.getContextPath()%>/login.jsp">[회원 로그인]</a>
    </li>
</ul>

<% } else { %>
<p><strong><%= loginUser.getName() %></strong> 님, 환영합니다 🙌</p>

<ul>
    <% if (loginUser.isAdmin()) { %>
    <!-- ✅ 관리자일 때: [관리자 - 회원관리] -->
    <li>
        <a href="<%=request.getContextPath()%>/admin">[관리자 - 회원관리]</a>
    </li>
    <% } else { %>
    <!-- ✅ 일반 사용자일 때: [마이페이지 정보수정] -->
    <li>
        <a href="<%=request.getContextPath()%>/mypage">[마이페이지 정보수정]</a>
    </li>
    <% } %>

    <li>
        <!-- 공통 로그아웃 -->
        <a href="<%=request.getContextPath()%>/logout">[로그아웃]</a>
    </li>
</ul>
<% } %>

</body>
</html>
