package com.cs122b.catsneeze.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Enumeration;

/*
AuthenticationFilter count the elapsedTime in search equest, write the elapsedTime in the log
 */
public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void destroy() {
        filterConfig = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // TS calculation
        // the average time it takes for the search servlet to run completely for a query
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        String servletPath = httpRequest.getServletPath();

        // Time an event in a program to nanosecond precision
        long startTime = System.nanoTime();
        // Pass request back down the filter chain
        chain.doFilter(req, resp);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // elapsed time in nano seconds. Note: print the values in nano seconds

        //write the elapsedTime into the log file
        System.out.println("!!!!!!!!!!!OuterelapsedTime is : " + elapsedTime/1000000.0 + "!!!!!!!!!!!!!!!");

        //can obtain current path, write the elapsedTime into a txt file in com.cs122b.catsneeze
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("./").getPath());

        FileWriter fw = null;
        try {
            File f=new File(Thread.currentThread().getContextClassLoader().getResource("./").getPath() + "../timeLog.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(" " + elapsedTime);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

}
