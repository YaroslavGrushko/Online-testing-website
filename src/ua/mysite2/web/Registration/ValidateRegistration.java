package ua.mysite2.web.Registration;

import ua.mysite2.web.servlets.Login;

import java.sql.*;

//import static ua.mysite2.web.servlets.Login.con;

//import static ConnectionToDB.ConnectionToDB.closeConnection;
//import static ConnectionToDB.ConnectionToDB.getConnection;

public class ValidateRegistration {



    public static boolean checkUser(String login,String password, String first_name,String last_name,String age,String students_group,String email)
    {
        boolean st =false;
        int age1 = 0;
        Connection connection = null;
        int id = 9;
        if(!age.equalsIgnoreCase(""))
        age1 = Integer.parseInt(age);
        String mybox="";
        int counter=0;
        try{
           //int id=9;
            id++;
            counter++;
            //loading drivers for mysql
            Class.forName("org.postgresql.Driver"); //throws class not found exception w/message "com.postgresql.jdbc.Driver"

            // opening database connection to PostgreSQL server
            connection = DriverManager.getConnection(Login.url, Login.user, Login.password); ;


            PreparedStatement ps = connection.prepareStatement
                    ("insert into register(id_u, username, pass, first_name, last_name, email, age, students_group) " +
                            "values (?,?,?,?,?,?,?,?) ");

            ps.setString(1, String.valueOf(id));
            ps.setString(2, login);
            ps.setString(3, password);
            ps.setString(4, first_name);
            ps.setString(5, last_name);
            ps.setString(6, email);
            ps.setInt(7, age1);
            ps.setString(8, students_group);
            ps.executeUpdate();



            // closeConnection(connection);

            st = true;
        }catch(Exception e)
        {
            st = false;
            if(counter<100){checkUser( login, password, first_name, last_name, age,students_group,email);}else{System.out.println(mybox+"Problems with validation of registration " + e);}
        }

        return st;
    }


}
