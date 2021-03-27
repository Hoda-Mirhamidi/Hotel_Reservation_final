package com.hotel.Hotel_Reservation_final.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "DateFilter")
public class DateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String sDate = req.getParameter("start");
        String eDate = req.getParameter("end");
        String regex1 = "^1400-(0[1-6]|[1-6])-(3[01]|[12][0-9]|0[0-9]|[0-9])$";
        String regex2 = "^1400-([7-9]|0[7-9]|1[0-2])-(30|[12][0-9]|0[0-9]|[0-9])$";
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher sDate_matcher1 = pattern1.matcher(sDate);
        Matcher sDate_matcher2 = pattern2.matcher(sDate);
        Matcher eDate_matcher1 = pattern1.matcher(eDate);
        Matcher eDate_matcher2 = pattern2.matcher(eDate);
        out.println("<html><body>");
        if((sDate_matcher1.matches() | sDate_matcher2.matches()) && (eDate_matcher1.matches() | eDate_matcher2.matches())){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date start = dateFormat.parse(sDate);
                Date end = dateFormat.parse(eDate);
                if(end.compareTo(start) > 0){
                    chain.doFilter(req,resp);
                }
                else {
                    out.println("Starting date has to precede the ending date !");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            out.println("Date input is invalid !");
        }
        out.println("</body></html>");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
