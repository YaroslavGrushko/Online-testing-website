<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.applet.Applet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.mysite2.web.servlets.Login" %>

<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 09.07.2016
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PrintWriter out_t = response.getWriter();
    out_t.println("<script type=\"text/javascript\">"+
    "document.var_question1"+
"</script>"+
"<html>"+
"<head>"+
    "<title>Title</title>"+
"</head>"+
"<meta charset=\"UTF-8\">"+
"<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />"+
"<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"all\" />"+
"<body bgcolor=\" 46 82 B4 \" text=\"black\">"+
"<br>");
%>
<%

//    String val2 ="student";
//    String val3 ="";
//    String textQuest ="";
//    String numQuest ="";
//    String numAnsw ="";
//    ArrayList<String> listAnsw= new ArrayList<String>();
//
//    String val_a_c ="";
  // try {
       String val2 = "student";
    String  val3 = (String) request.getAttribute("val1");
    String   textQuest = (String) request.getAttribute("valQ");
    String  numQuest = (String) request.getAttribute("valQ_N");
    String  numAnsw = (String) request.getAttribute("valA_N");

    String  true_answ = (String) request.getAttribute("trueAnsw");
  // ArrayList<String>  listAnsw = (ArrayList<String>) request.getAttribute("valA");
    ArrayList<String>  listAnsw=(ArrayList<String>)Login.s.getAttribute("answers");
       //String numQuest2 =numQuest;
       //String numQuest3 =numQuest;

    String    val_a_c = (String) request.getAttribute("val_a_c");
  // }catch(Exception e){System.out.println(e);}
    //request.setAttribute("valQ_N1",numQuest);

int true_answ1=Integer.valueOf(true_answ);

    if(val3.equals("admin")){val2=val3;}
    int a = 5;
    int b = a + 10;

    out_t.println("Role="+val2);
    out_t.println("Name="+val3);

    String Greating="";%>


<%   if(val2.equals("admin")){%>
<script type="text/javascript"> document.getElementById("quest").readOnly = false;</script>
<%Greating="Hello admin!";
    //out_t.println("Hello admin!");
}else{%>
<script type="text/javascript"> document.getElementById("quest").readOnly = true;</script>
<%Greating="Hello student!";

// out_t.println("Hello student!");
}
%>
<%
    out_t.println("<p align=\"center\">"+Greating+"</p>");
    if(val2.equals("admin"))out_t.println("<br/> val_of_c="+val_a_c);
    %>

<%
    out_t.println(""+





"<div id=\"container\">"+
   " Question" +
            //форма перехода на другой вопрос:
            "<form method=\"post\" action=\"nextQuestion\">" +
            " <input id=\"id_nq\" type=\"text\" name=\"questionNum\" size=\"1\" value=\""+numQuest+"\" onkeyup=\"copyValueTo(this, 'valQ_N1')\" />" +
                    "<input id=\"input6\" type=\"hidden\" name=\"answerCount\" value=\""+numAnsw+"\"/>"+
            "<input type=\"submit\" value=\"questionNum\" />"+
            "</form>"+
            "<br>");
    //Форма принятия изменений:
            out_t.println("<form method=\"post\" action=\"acceptChanges\">"+
            //"<br/>"+
           //"<input type=\"hidden\" name=\"valQ_N1\" value=\"1\">"+
            //"<input id=\"input2\" type=\"text\" name=\"valQ_N1\" value=\""+numQuest2+"\">"+
            "<input id=\"input2\" type=\"hidden\" name=\"valQ_N1\" value=\""+numQuest+"\">"+
            "<input id=\"input7\" type=\"hidden\" name=\"answerCount\" value=\""+numAnsw+"\"/>"+
            "<input type=\"hidden\" name=\"inpChekAnsw\" value=\""+true_answ+"\"/>");
    if(val2.equals("admin")){out_t.println("<textarea id=\"quest\" name=\"question\"  cols=\"108\" rows=\"3\"/>"+textQuest+"</textarea>");}else{out_t.println(textQuest);};
   //дублмрующие ответы:
    for(int i=1;i<= Integer.parseInt(numAnsw);i++) {
        out_t.println("<textarea id=\"ans"+i+"\" name=\"anstext1"+i+"\" style=\"display:none;\" cols=\"80\" rows=\"1\" />"+listAnsw.get(i-1)+"</textarea>");
                                                   }
            if(val2.equals("admin")){out_t.println("<input type=\"submit\" value=\"acceptChanges\" /> Questions should not be repeated!");};

    out_t.println("<br/>"+
    "</form>"+
   //конец формы принятия изменений
            "<fieldset>"+
        "Answers");
        //"<p>"+
         //   "<br/>"+
    out_t.println("<form method=\"post\" action=\"acceptAnswers\">"+
    "<input id=\"input3\" type=\"hidden\" name=\"valQ_N1\" value=\""+numQuest+"\">"+
    "<input id=\"input5\" type=\"hidden\" name=\"answerCount\" value=\""+numAnsw+"\"/>");
//Вывод вариантов ответов:
    for(int i=1;i<= Integer.parseInt(numAnsw);i++) {
               out_t.println("<br/>"+"<input type=radio");if(val2.equals("admin")&&(true_answ1==i))out_t.println(" checked"); out_t.println(" name=\"ans\" value=\""+i+"\" onchange=\"copyValueTo(this, 'inpChekAnsw')\"> ");
        if(val2.equals("admin")){
            out_t.println("<textarea id=\"ans"+i+"\" name=\"anstext"+i+"\"  cols=\"80\" rows=\"1\" onkeyup=\"copyValueTo(this, 'anstext1"+i+"')\"/>"+listAnsw.get(i-1)+"</textarea>");
                                }else{
            out_t.println(listAnsw.get(i-1));
                                     }
       // +listAnsw.get(i-1)/*+i*/);
           }
    if(val2.equals("student")) {
        out_t.println("<br>" +
                // "<input type=radio name=\"ans\" value=\"2\"> ansver2"+
                "<input type=\"submit\" value=\"acceptAnswers\" />");
                               }
    out_t.println("</form>");
        //"</p>"+
            out_t.println("</fieldset>"+

"</div>");

%>

<!--<form method="post" action="acceptChanges">
        <br/>
        <textarea id="quest" name="question"  cols="111" rows="3"/>"+textQuest+"</textarea>
        <br/>
<input type="submit" value="acceptChanges" />
    <br/>
</form>-->


<%--<%out_t.println("<form>"+--%>
        <%--//"<input class=\"ip1\" type=\"text\" value=\"\" onchange=\"alert(this.value)\">  />"+--%>
        <%--//"<input class=\"ip1\" type=\"text\" value=\"\" onchange=\"script1.js\">  />"+--%>
        <%--"<input class=\"ip2\" type=\"text\"  value=\"\" />"+--%>
    <%--"<input type=\"button\" value=\"Кнопка\">"+--%>
<%--"</form>");--%>
<%--%>--%>



<%--<input id="input3" type="text" name="valQ_N2" value="" onkeyup="copyValueTo(this, 'valQ_N3')" />--%>
<%--<input id="input8" type="text" name="valQ_N3" value=""/>--%>
<%--<input id="input9" type="text" name="valQ_N3" value=""/>--%>

<%--<textarea id="ans_1" name="anstex5"  cols="30" rows="1" onkeyup="copyValueTo(this, 'anstex6')"/></textarea>--%>
<%--<textarea id="ans_2" name="anstex6"  cols="30" rows="1"/></textarea>--%>
<%--<textarea id="ans_3" name="anstex6" style="display:none;"  cols="30" rows="1"/></textarea>--%>


<%--<input type=radio name="ans2" value="1" onchange="copyValueTo(this, 'inpChekAnsw')"> tes answ1--%>
<%--<input type=radio name="ans2" value="2" onchange="copyValueTo(this, 'inpChekAnsw')"> tes answ2--%>
<%--<input type="text" name="inpChekAnsw" value=""/>--%>


<script type="text/javascript">
function copyValueTo(fromElem, toElemId) {
var elems = document.getElementsByName(toElemId);
 for (var i = 0; i < elems.length; i++) {
elems[i].value = fromElem.value;        }
}
</script>


<%
    out_t.println("</body>"+
"</html>");
%>


<% // while(true){numQuest2 =numQuest;}   %>