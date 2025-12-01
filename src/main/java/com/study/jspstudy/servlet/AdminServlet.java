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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User loginUser = (session != null)
                ? (User) session.getAttribute("loginUser")
                : null;

        // 로그인 안 했으면 로그인 페이지로
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 관리자가 아니면 메인으로
        if (!loginUser.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
            return;
        }

        // ✅ 전체 회원 목록 조회해서 admin.jsp로 전달
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
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

        if (loginUser == null || !loginUser.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");  // delete / grantAdmin / revokeAdmin
        String idStr = request.getParameter("id");

        if (idStr != null && action != null) {
            int id = Integer.parseInt(idStr);

            switch (action) {
                case "delete":
                    userDAO.deleteById(id);
                    break;
                case "grantAdmin":
                    userDAO.updateAdminFlag(id, true);
                    break;
                case "revokeAdmin":
                    userDAO.updateAdminFlag(id, false);
                    break;
            }
        }

        // 작업 후 다시 목록으로
        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
