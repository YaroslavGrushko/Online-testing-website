package ua.mysite2.web.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Created by Ярослав on 06.07.2016.
 */
public class Welcome extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String userRole="admin";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //request.setAttribute("val1", userRole);
		//RequestDispatcher rs = request.getRequestDispatcher("/test2.jsp");
          //  rs.forward(request, response);
        out.println("Welcome user");
        out.println("<br><br>");

        out.println("<html>" +
        "<meta charset=\"UTF-8\">"+
        "<body>"+
                "<?php"+
               " error_reporting(E_ALL);"+
                "error_reporting(0); //отключить вывод ошибок и предупреждений\n" +
                        "\n" +
                        " session_start();  // открываем сессию (возможность создать глобальную переменную)"+
                "$_SESSION[\"client\"] = 1"+
                "?>"+
       // out.println("<h1>Hello Servlet Get</h1>");
        "<br><br><br>"+
        //out.println("<p align=center><input type=submit value='test' /></p>");
       "<li>"+
        //"<a href=\"/setParamTotest\" action=\"login\">Test</a>"+
      "<form method=\"post\" action=\"setParamTotest\">"+
                "<input type=\"submit\" value=\"test\" />\n" +
                "</form>"+
       "</li>"+
        "</form>"+
       "</body>"+
        "</html>");

        out.flush();


    }
}

