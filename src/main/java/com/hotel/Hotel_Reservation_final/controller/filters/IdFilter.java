package com.hotel.Hotel_Reservation_final.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "IdFilter")
public class IdFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        String regex ="\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        out.println("<html><body>");
        if(matcher.matches()){
            chain.doFilter(req, resp);
        }
        else{
            out.println("Id input is invalid !");
        }
        out.println("</body></html>");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
