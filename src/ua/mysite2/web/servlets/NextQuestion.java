package ua.mysite2.web.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static ua.mysite2.web.servlets.Login.con;

/**
 * Created by Ярослав on 11.07.2016.
 */
public class NextQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  response.setContentType("text/html;charset=UTF-8");
        // PrintWriter out = response.getWriter();
        String question;
        ArrayList<String>  answList=new ArrayList<String>();
        String TrueAnswer="";
        //получаем данные из запроса:
        String questionNum =  (String)request.getParameter("questionNum");
        String AnswCount =  (String)request.getParameter("answerCount");
       // ArrayList<String>  answList = (ArrayList<String>) request.getParameter("valA");
        question="Now all right";
        //Вытаскиваем вопрос по его id из базы:
        try {
            PreparedStatement ps2 =con.prepareStatement
                    ("select * from questions where id_q=?");
            ps2.setString(1, questionNum);
            ResultSet rs2 =ps2.executeQuery();
            rs2.next();
            question=rs2.getString(2);
            ps2.close();
        }catch(Exception e){question="Error. Specify the number of the non-existent question. A total of 6 issues.";}

        //считываем количество ответов на данный вопрос
        try {PreparedStatement ps3 =con.prepareStatement
                ("select count(*) from AllAnswers where id_q=?");

            ps3.setString(1, questionNum);
            ResultSet rs3 =ps3.executeQuery();
            rs3.next();
            AnswCount=rs3.getString(1);
            ps3.close();

        }catch(Exception e){question+="Error. Problem with table UserAnswer.";}

//считываем ответы для данного вопроса:
        try {
            for (int i = 0; i < Integer.parseInt(AnswCount); i++) {
                PreparedStatement ps4 = con.prepareStatement
                        ("select a_text from AllAnswers where id_q=? and id_a=?");
                ps4.setString(1, questionNum);
                ps4.setString(2, String.valueOf(i+1));
                ResultSet rs4 = ps4.executeQuery();
                rs4.next();

                answList.add(i, rs4.getString(1));
                ps4.close();
            }
        }catch(Exception e){System.out.println(e);}


        //вытаскиваем номер правильного ответа  из таблицы AllAnswers для выбранного вопроса:
        try {

            PreparedStatement ps5 = con.prepareStatement
                    ("select id_a from AllAnswers where id_q=? and iscorrect=?");
            ps5.setString(1, questionNum);
            ps5.setString(2, "true");
            ResultSet rs5 = ps5.executeQuery();
            rs5.next();

            TrueAnswer=rs5.getString(1);

            ps5.close();
        }catch(Exception e){TrueAnswer="0"; System.out.println(e);}



        Login.s.setAttribute("answers",answList);//устанавливаем сессионную переменную "ответы на данный вопрос"
        int val1=0;
        int val7=0;
        try{val1=(int)Login.s.getAttribute("val_a_c1");}catch(Exception e){
            Login.s.setAttribute("val_a_c1",val7);
        }
        Login.s.setAttribute("val_a_c1",val1);


        String val2=Integer.toString(val1);
        request.setAttribute("val_a_c", val2);
        request.setAttribute("val1", Login.userRole);
        request.setAttribute("valQ", question);
        request.setAttribute("valQ_N", questionNum);
        request.setAttribute("valA_N", AnswCount);

        request.setAttribute("trueAnsw", TrueAnswer);
        //request.setAttribute("valA", /*Login.qlist*/);
        //request.setAttribute("valA_N", Login.AnswersCount);
        RequestDispatcher rs = request.getRequestDispatcher("/test2.jsp");
        rs.forward(request, response);

        //out.println("questionNum is:"+questionNum+"|");
    }

}
