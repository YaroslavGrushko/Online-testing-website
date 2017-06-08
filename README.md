# Web-site-for-passing-tests
The website is designed for students and teachers.  Students can register and log in to take the test and get an mark.  The teacher can enter as an administrator (login: admin and password: 000)  and change questions, answers. Also the teacher can mark the correct  answer using the appropriate radio button. 

The number of questions can be any. Initially, there are 5 questions in the DB.
You can add new ones manually in the database. Table containing questions is:
"questions". The question id is id_q.
Table containing the answers is: "allanswers". The question id is also "id_q", 
the answer id is "id_a".

# To launch a website, follow these instructions:

1.Install JDK (I'm using jdk1.8.0_73).  
2.Install Intellij IDEA (set JDK in Intellij IDEA).  
2.Set the JAVA_HOME Variable.  
3.Install PostgreSQL (username="postgres", password="root").  
4.Unpackage DB dump ( Web-site-for-passing-tests\DB\TrainDb_dump.sql).  
5.Tomcat server is already installed in the directory:  Web-site-for-passing-tests\localtest.  
6.Run the project (You may need to open a browser and go to url: http://localhost/).

