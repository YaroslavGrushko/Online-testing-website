package ua.mysite2.web.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Ярослав on 09.07.2016.
 */
public class SetParamTotest extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();

        Login.s.setAttribute("answers",Login.qlist);

        request.setAttribute("valQ_N", Login.id_q);
        request.setAttribute("valA_N", Login.AnswersCount);
        request.setAttribute("val1", Login.userRole);
        request.setAttribute("valQ", Login.TextQuest);
        request.setAttribute("trueAnsw",Login.Tuue_Answer);
        //request.setAttribute("valA", Login.qlist);
        RequestDispatcher rs = request.getRequestDispatcher("/test2.jsp");
        rs.forward(request, response);
//        out.println("Answers Count is :"+Integer.parseInt(Login.AnswersCount));
//            out.println("Answers are: ");
        //тут парсинг стринга в инт:
//        try{
//        for(int i=0;i<Integer.parseInt(Login.AnswersCount);i++){
//            out.println("Ans "+(i+1)+" is :"+Login.qlist.get(i));
//        }
//        }catch (Exception e){System.out.println(e);}

    }

}
