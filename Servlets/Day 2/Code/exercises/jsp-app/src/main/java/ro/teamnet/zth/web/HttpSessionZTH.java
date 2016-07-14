package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password=request.getParameter("password");

        Cookie[] cookie=request.getCookies();
        // Set the response type
        response.setContentType("text/html");

        if (user.equals("admin") && password.equals( "admin")) {
            response.getWriter().write("<p>Welcome back: "+user+"</p>");
            response.getWriter().write(String.valueOf(request.getSession()));
        }else{
            request.setAttribute("user",user);
            request.setAttribute("session",String.valueOf(request.getSession()));

            response.sendRedirect("/views/loginFail.jsp");
        }
    }
}
