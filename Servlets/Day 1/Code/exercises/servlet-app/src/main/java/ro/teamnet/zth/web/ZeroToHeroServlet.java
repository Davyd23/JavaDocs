package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/12/2016.
 */
public class ZeroToHeroServlet extends HttpServlet {

    private String handleRequest(HttpServletRequest req){
        String firstName=req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String response="Hello <b>"+firstName+" "+lastName+"</b>! Enjoy Zero To Hero!!!";

        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html");

        resp.getWriter().write(handleRequest(req));
    }
}
