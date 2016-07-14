package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpServletInclude extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set the response type
        response.setContentType("text/html");


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/included");
        request.setAttribute("include", "Enjoy Z2H, this is included servlet!");

        requestDispatcher.include(request, response);

        response.getWriter().write("<p>Called from include class!</p>");

    }
}
