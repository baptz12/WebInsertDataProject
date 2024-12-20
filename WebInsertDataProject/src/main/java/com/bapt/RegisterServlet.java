package com.bapt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException 
  {
    int resUpdate = 0;
    String URL = "jdbc:mysql://localhost:3306/demo";
    String USERNAME = "root";
    String PASSWORD = "root";
    
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password1 = req.getParameter("password1");
    String password2 = req.getParameter("password2");
    Date date = new Date();
    Timestamp timeStamp = new Timestamp(date.getTime());
    
    if (password1 != null && password2 != null && password1.equals(password2) == false) {
      showErrorPage(res, "Password must be identical !");
      return;
    }
    if (username == null || username.isEmpty() || email == null || email.isEmpty() || password1 == null || password1.isEmpty() || password2 == null || password2.isEmpty()) {
      showErrorPage(res, "Missing informations !");
      return;
    }
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement insertUser = connection.prepareStatement(
            "INSERT into members (username, email, password, date_of_creation) VALUES (?, ?, ?, ?)")) {
      
      insertUser.setString(1, username);
      insertUser.setString(2, email);
      insertUser.setString(3, password1);
      insertUser.setTimestamp(4, timeStamp);
      resUpdate = insertUser.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        showErrorPage(res, "Database error has occurred.");
        return;
    }
    if (resUpdate == 1) {
      showSuccessPage(res, req);
    } else {
      showErrorPage(res, "Error with register, please retry.");
    }
  }
  
  private void showErrorPage(HttpServletResponse res, String errorMessage) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Error</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='error'>" + errorMessage + "</div>");
    out.print("</body></html>");
  }
  
  private void showSuccessPage(HttpServletResponse res, HttpServletRequest req) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Success</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='success'>Registered successfully !</div>");
    out.print("<form action='login' method='get'>");
    out.print("<button type='submit' class='back_button' name='exit_button'>Back</button>");
    out.print("</form>");
    out.print("</body></html>");
  }
}
