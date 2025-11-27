package com.study.jspstudy.filter;

import com.study.jspstudy.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/exam/*", "/mypage"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User loginUser = (session != null)
                ? (User) session.getAttribute("loginUser")
                : null;

        String path = req.getServletPath();

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if ("/admin".equals(path) && !loginUser.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
