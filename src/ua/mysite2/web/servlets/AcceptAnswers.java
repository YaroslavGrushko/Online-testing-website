package ua.mysite2.web.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static ua.mysite2.web.servlets.Login.con;

/**
 * Created by Ярослав on 12.07.2016.
 */
public class AcceptAnswers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        String question="Default";
        String ans1 = (String)request.getParameter("ans");
        String TAns;
        String questionNum=(String)request.getParameter("valQ_N1");
        String ErrBox;
        TAns="all right";
        //Работа с БД:
        //вытягиваем правильность ответа студента
        try {

            PreparedStatement ps =Login.con.prepareStatement
                    ("select isCorrect from AllAnswers where id_q=? and id_a=?");
            //select isCorrect from AllAnswers where id_q='2' and id_a='2'
            ps.setString(1, questionNum);
            ps.setString(2, ans1);
            ResultSet rs =ps.executeQuery();
            rs.next();
            TAns=rs.getString(1);
            ps.close();

        }catch(Exception e){TAns="Error. Sorry in the table of correct answers there is no this question.";}
        //проверяем есть ли уже запись с нашими id_u и id_q в таб. UserAnswers:
        ErrBox="all right";
        boolean st =false;
        try {
            PreparedStatement ps2 =Login.con.prepareStatement
                    ("select * from UserAnswers where id_u=(Select id_u from register where username=?) and id_q=? ");
            //select * from UserAnswers where id_u=(Select id_u from register where username='A') and id_q='1'
            ps2.setString(1, Login.userRole);
            ps2.setString(2, questionNum);
            ResultSet rs2 =ps2.executeQuery();
            st = rs2.next();
            ps2.close();
            PreparedStatement ps3;
            PreparedStatement ps4;
            if(st){
                 ps3 =Login.con.prepareStatement
                        ("update UserAnswers set result=? where id_u=(Select id_u from register where username=?) and id_q=? ");
                //update UserAnswers set result='false' where id_u='5' and id_q='1'
                ps3.setString(1, TAns);
                ps3.setString(2, Login.userRole);
                ps3.setString(3, questionNum);
                ps3.executeUpdate();
                ps3.close();

                 }else{
                 ps4 =Login.con.prepareStatement
                        (" insert into UserAnswers values((Select id_u from register where username=?), ?, ?)");
                //insert into UserAnswers values((Select id_u from register where username='A'), '2', 'false');
                ps4.setString(1, Login.userRole);
                ps4.setString(2, questionNum);
                ps4.setString(3, TAns);
                ps4.executeUpdate();
                ps4.close();

                      }


        }catch(Exception e){ErrBox="Error. Problem with table UserAnswer.";}



        out.println("questionNum is /"+questionNum+"/ and ans is /"+ans1+"/ <br/>");
        out.println("Your answer to the question "+question+" is !!!"+TAns+"!!!");
        out.println("<br/> isExist="+st);
        out.println("ErrBox= "+ErrBox);

    }
}
