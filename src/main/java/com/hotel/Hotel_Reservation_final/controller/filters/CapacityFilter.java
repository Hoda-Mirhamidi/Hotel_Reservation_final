package com.hotel.Hotel_Reservation_final.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "CapacityFilter")
public class CapacityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        String capacity = req.getParameter("capacity");
        String regex ="^[1-4]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(capacity);
        if(matcher.matches()){
            chain.doFilter(req, resp);
        }
        else{
            out.println("Capacity input is invalid !");
        }
        out.println("</body></html>");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
