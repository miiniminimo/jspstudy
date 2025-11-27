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

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User loginUser = (session != null)
                ? (User) session.getAttribute("loginUser")
                : null;

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("user", loginUser);
        request.getRequestDispatcher("/mypage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User loginUser = (session != null)
                ? (User) session.getAttribute("loginUser")
                : null;

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String schoolName = request.getParameter("schoolName");
        String grade = request.getParameter("grade");
        String email = request.getParameter("email");

        loginUser.setPassword(password);
        loginUser.setName(name);
        loginUser.setSchoolName(schoolName);
        loginUser.setGrade(grade);
        loginUser.setEmail(email);

        boolean ok = userDAO.updateUser(loginUser);

        if (ok) {
            request.setAttribute("msg", "정보가 성공적으로 수정되었습니다.");
        } else {
            request.setAttribute("msg", "정보 수정에 실패했습니다. 다시 시도해주세요.");
        }

        request.setAttribute("user", loginUser);
        request.getRequestDispatcher("/mypage.jsp").forward(request, response);
    }
}
