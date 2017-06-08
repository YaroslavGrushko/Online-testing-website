<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 09.07.2016
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <form method="post" action="registration" >
        <div class="outer">
        <ol type="1">
            <li>Your first name *:<input class="inner inner1" type="text" name="first_name" /></li>
            <li>Last name *:<input class="inner inner2" type="text" name="last_name" /></li>
            <li>Password *:<input class="inner inner3" type="password" name="password" /></li>
            <li>Password *:<input class="inner inner4" type="password" name="password2" /></li>
            <li>Login *:<input class="inner inner5" type="text" name="login" /></li>
            <li>Email *:<input class="inner inner6" type="email" name="email" /></li>
            <li>Group :<input class="inner inner7" type="text" name="group" /></li>
            <li>Age :<input class="inner inner8" type="number" name="age" /></li>
        </ol>
        </div>
        <input class="inner inner9 innerButton" type="submit" value="sign up" title="Enter"/>
    </form>
</body>
</body>
</html>
