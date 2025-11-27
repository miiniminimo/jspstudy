package com.study.jspstudy.servlet;

import com.study.jspstudy.dao.UserDAO;
import com.study.jspstudy.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = userDAO.login(userId, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);

            // ✅ 관리자면 /admin 으로, 일반 유저면 /mypage 로 보내기
            if (user.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/mypage");
            }
        } else {
            request.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
