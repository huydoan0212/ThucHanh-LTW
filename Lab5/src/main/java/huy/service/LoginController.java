package huy.service;

import huy.bean.User;
import huy.bean.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        User user = UserService.getInstance().checkLogin(email,pass);
        if (user!=null){
            HttpSession session = req.getSession();
            session.setAttribute("auth", user);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Bạn nhập sai email hoặc password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}