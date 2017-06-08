package ua.mysite2.web.servlets;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {

    // JDBC URL, username and password of MySQL server
    public static final String url = "jdbc:postgresql://localhost:5432/TrainDb";
    public static final String user = "postgres";
    public static final String password = "root";

    // JDBC variables for opening and managing connection
    //private static Connection con;
    public static Connection con;
//    private static Statement stmt;
//    private static ResultSet rs;
//    private static ResultSet rs2;

    //public сессия:
    public static HttpSession s;

    //роль пользователя:
    public static String userRole;
    public static String TextQuest;
    public static String AnswersCount;
    public static  String id_q="2";
    public static  String Tuue_Answer="";
    public static int myid;
    public static ArrayList<String> qlist;

    //public static int val_a_c=0;//колличество изменений
       public boolean checkUser(String username,String pass)
    {
        qlist = new ArrayList<String>();
    //String id_q="2";
        String id_q2="2";
        boolean st =false;
        try{

            //loading drivers for mysql
            Class.forName("org.postgresql.Driver"); //throws class not found exception w/message "com.postgresql.jdbc.Driver"

            // opening database connection to PostgreSQL server
            con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps =con.prepareStatement
                    ("select * from register where username=? and pass=?");
            PreparedStatement ps2 =con.prepareStatement
                    ("select * from questions where id_q=?");
            PreparedStatement ps3 =con.prepareStatement
                   ("select count(*) from AllAnswers where id_q=?");

            //select count(*) from AllAnswers where id_q='2';
//            PreparedStatement ps4 =con.prepareStatement
//                    ("select a_text from AllAnswers where id_q=?");
           // Statement st2 =con.createStatement();

            ps.setString(1, username);
            ps.setString(2, pass);

            ps2.setString(1, id_q);

            ps3.setString(1, id_q2);

            //ps4.setString(1, id_q);

            ResultSet rs =ps.executeQuery();
            ResultSet rs2 =ps2.executeQuery();
            ResultSet rs3 =ps3.executeQuery();
//            ResultSet rs4 =ps4.executeQuery();
            //ResultSet rs2 =st2.executeQuery("select * from questions where id_q='1'");
            st = rs.next();
            rs2.next();
            rs3.next();
//            rs4.next();
     userRole=rs.getString(2);
            TextQuest=rs2.getString(2);
            AnswersCount=rs3.getString(1);
            //qlist=(ArrayList<String>)rs4.getArray(1);
          try {
              for (int i = 0; i < Integer.parseInt(Login.AnswersCount); i++) {
                  PreparedStatement ps4 = con.prepareStatement
                          ("select a_text from AllAnswers where id_q=? and id_a=?");
                  ps4.setString(1, id_q);
                  ps4.setString(2, String.valueOf(i+1));
                  ResultSet rs4 = ps4.executeQuery();
                  rs4.next();

                   qlist.add(i, rs4.getString(1));
                  ps4.close();
              }
          }catch(Exception e){System.out.println(e);}

//вытаскиваем номер правильного ответа  из таблицы AllAnswers для вопроса id_q:
            try {

                    PreparedStatement ps5 = con.prepareStatement
                            ("select id_a from AllAnswers where id_q=? and iscorrect=?");
                    ps5.setString(1, id_q);
                    ps5.setString(2, "true");
                    ResultSet rs5 = ps5.executeQuery();
                    rs5.next();

                    Tuue_Answer=rs5.getString(1);

                    ps5.close();
            }catch(Exception e){Tuue_Answer="0"; System.out.println(e);}

            ps.close();
            ps2.close();
            ps3.close();

             //myid=rs2.getInt(1);
            //if(st)out.println("Connection is done!");

        }catch(Exception e)
        {
             System.out.println(e);
        }

return st;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        s = request.getSession(true);


        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        out.println(" Servlet see " + username);


       if(checkUser(username, pass))
        {
            out.println(" TextQuest "+ TextQuest);

            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        }
        else
        {
            out.println(" Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("mypage.html");
            rs.include(request, response);
        }
    }
}