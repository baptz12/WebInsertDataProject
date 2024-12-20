package com.bapt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
  {
    String URL = "jdbc:mysql://localhost:3306/demo";
    String USERNAME = "root";
    String PASSWORD = "root";
    
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    HttpSession session = req.getSession();
    RequestDispatcher dispatcher = null;
    
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      showErrorPage(res, "Missing informations !");
      return;
    }
    
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement selectMember = connection.prepareStatement(
            "SELECT * from MEMBERS where username = ? and password = ?")) {
          
        selectMember.setString(1, username);
        selectMember.setString(2, password);
 
        ResultSet rs = selectMember.executeQuery();
        if (rs.next()) {
          session.setAttribute("username", rs.getString("username"));
          dispatcher = req.getRequestDispatcher("index.jsp");
        } else {
          showWrongLoginPage(res, "Wrong username or password");
          return;
        }
        dispatcher.forward(req, res);
        
        } catch (SQLException e) {
          e.printStackTrace();
          showErrorPage(res, "Database error has occurred.");
          return;
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
  
  private void showWrongLoginPage(HttpServletResponse res, String errorMessage) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Error</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='error'>" + errorMessage + "</div>");
    out.print("<form action='login' method='get'>");
    out.print("<button type='submit' class='back_button' name='exit_button'>Back</button>");
    out.print("</form>");
    out.print("</body></html>");
  }
}
