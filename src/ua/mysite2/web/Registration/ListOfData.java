package ua.mysite2.web.Registration;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet ("/registration")
public class ListOfData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String age = request.getParameter("age");
        String group = request.getParameter("group");
        String email = request.getParameter("email");

        if(password2.equalsIgnoreCase(password)){
            if(ValidateRegistration.checkUser(login,password,first_name,last_name,age,group,email))
            {
                out.println("Registration complete");
                RequestDispatcher rs = request.getRequestDispatcher("mypage.html");
                rs.forward(request, response);
            }
            else
            {
                out.println("Enter data");
                RequestDispatcher rs = request.getRequestDispatcher("registration.jsp");
                rs.include(request, response);
            }
        }
        else
        {
            out.println("Incorrect second password");
            RequestDispatcher rs = request.getRequestDispatcher("registration.jsp");
            rs.include(request, response);
        }
    }
}
