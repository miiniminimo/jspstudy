package com.study.jspstudy.servlet;

import com.study.jspstudy.dao.UserDAO;
import com.study.jspstudy.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String schoolName = request.getParameter("schoolName");
        String grade = request.getParameter("grade");
        String email = request.getParameter("email");

        User user = new User(userId, password, name, schoolName, grade, email);

        boolean ok = userDAO.insertUser(user);
        if (ok) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.setAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}
