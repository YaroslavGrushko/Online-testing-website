package ua.mysite2.web.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import static ua.mysite2.web.servlets.Login.con;

/**
 * Created by Ярослав on 10.07.2016.
 */
public class AcceptChanges extends HttpServlet {
    //private static Statement stmt;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String question = (String)request.getParameter("question");
        //String questionNum =  (String)request.getParameter("questionNum");
        String questionNum=(String)request.getParameter("valQ_N1");
        String AnswCount =  (String)request.getParameter("answerCount");

        String TrueAnsw =  (String)request.getParameter("inpChekAnsw");
//if(TrueAnsw==""){TrueAnsw="0";}

       // out.println("TrueAnsw ="+TrueAnsw);

        ArrayList<String>  ListAnsw = new ArrayList<String>();

       //принимаем значения измененных администратором вариантов ответов
        for(int i=1; i<=Integer.parseInt(AnswCount);i++){

                     String str= (String)request.getParameter("anstext1"+Integer.valueOf(i));
                             ListAnsw.add(str);
                                                        }
        //for(int i=1; i<=Integer.parseInt(AnswCount);i++)out.println("answ"+i+" is: "+ ListAnsw.get(i-1));



        //изменяем значения вариантов ответов в табдице AllAnswers:
        try {
            for (int i = 0; i < Integer.parseInt(AnswCount); i++) {
                PreparedStatement ps =con.prepareStatement
                        ("update AllAnswers set a_text =?, iscorrect=? where id_q=? and id_a=?");
                //ArrayList.get(i)
                ps.setString(1, ListAnsw.get(i));
                ps.setString(2, "false");
                ps.setString(3, questionNum);
                ps.setString(4, String.valueOf(i+1));

                ps.executeUpdate();
                ps.close();
            }
        }catch(Exception e){question="Text sizes is too big. Answer is not changed.";}



//изменяем значения вопросов в таблице вопросов(в БД):
        try {
            PreparedStatement ps =con.prepareStatement
                    ("update questions set q_text =? where id_q=?");
            ps.setString(1, question);

            ps.setString(2, questionNum);
          ps.executeUpdate();
            ps.close();
        }catch(Exception e){question+=" Text sizes is too big. Question is not changed.";}

        //ставим маркер "true" напротив правильного ответа в таб. AllAnswers:
        try {
            PreparedStatement ps =con.prepareStatement
                    ("update AllAnswers set iscorrect =? where id_q=? and id_a=?");

            ps.setString(1, "true");
            ps.setString(2, questionNum);
            ps.setString(3, TrueAnsw);

            ps.executeUpdate();
            ps.close();
        }catch(Exception e){TrueAnsw="0"; question+=" Error. Trouble with attr \"iscorrect\" in AllAnswers table.";}



      //if(TrueAnsw==""){TrueAnsw="0";}
       // out.println(" Servlet see " + questionNum);

        Login.s = request.getSession(true);
        int val1=0;
        int val7=0;
        try{val1=(int)Login.s.getAttribute("val_a_c1");}catch(Exception e){
            Login.s.setAttribute("val_a_c1",val7);
        }

        val1++;
        Login.s.setAttribute("val_a_c1",val1);

        Login.s.setAttribute("answers",ListAnsw);
        //out.println("TrueAnsw ="+TrueAnsw);

        String val2=Integer.toString(val1);
        request.setAttribute("val_a_c", val2);
        request.setAttribute("val1", Login.userRole);
        request.setAttribute("valQ", question);
        request.setAttribute("valQ_N", questionNum);
        request.setAttribute("valA_N", AnswCount);
        request.setAttribute("trueAnsw", TrueAnsw);

        RequestDispatcher rs = request.getRequestDispatcher("/test2.jsp");
        rs.forward(request, response);
    }
}
