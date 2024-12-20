package com.bapt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException 
  {
      int resUpdate = 0;
      String URL = "jdbc:mysql://localhost:3306/demo";
      String USERNAME = "root";
      String PASSWORD = "root";
      if (req.getParameter("db_button") != null) {
        try
        {
          showDataBasePage(res);
        }
        catch (IOException | SQLException e)
        {
          e.printStackTrace();
        }
        return;
      }
      String prenom = req.getParameter("prenom");
      String nom = req.getParameter("nom");
      Date date = new Date();
      Timestamp timeStamp = new Timestamp(date.getTime());
      
      if (prenom == null || prenom.isEmpty() || nom == null || nom.isEmpty()) {
        showErrorPage(res, "Error with request, please retry.");
        return;
      }
      try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          PreparedStatement insertUser = connection.prepareStatement(
              "INSERT into USERS (prenom, nom, derniere_maj) VALUES (?, ?, ?)")) {
        
        insertUser.setString(1, prenom);
        insertUser.setString(2, nom);
        insertUser.setTimestamp(3, timeStamp);
        resUpdate = insertUser.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
 
          showErrorPage(res, "Database error has occurred.");
          return;
      }
      if (resUpdate == 1) {     
        showSuccessPage(res);
      } else {
        showErrorPage(res, "Error with request, please retry.");
      }
  }

  private void showSuccessPage(HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Success</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='success'>User successfully added!</div>");
    out.print("<form action='pre_add' method='get'>");
    out.print("<button type='submit' class='right_back_button' name='exit_button'>Back</button>");
    out.print("</form>");
    out.print("</body></html>");
  }
  
  private void showErrorPage(HttpServletResponse res, String errorMessage) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Error</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='error'>" + errorMessage + "</div>");
    out.print("<form action='pre_add' method='get'>");
    out.print("<button type='submit' class='right_back_button' name='exit_button'>Back</button>");
    out.print("</form>");
    out.print("</body></html>");
  }
  
  private void showDataBasePage(HttpServletResponse res) throws IOException, SQLException 
  {
      class User {
      String URL = "jdbc:mysql://localhost:3306/demo";
      String USERNAME = "root";
      String PASSWORD = "root";
      
      Connection connection = null;
      PreparedStatement selectUser = null;
      ResultSet resultSet = null;
      
      public User() {
        try {
          
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          
          selectUser = connection.prepareStatement(
              "SELECT * from users");
          
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      public ResultSet getUsers() {
        try {
          resultSet = selectUser.executeQuery();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return resultSet;
      }
    }

    User user =  new User();
    ResultSet users = user.getUsers();

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>Databases content</title><link rel='stylesheet' type='text/css' href='style.css'></head>");
    out.print("<body>");
    out.print("<div class='db_page'>");
    out.print("<table class='table_db'>");
    out.print("<tbody class='tbody_db'>");
    out.print("<tr class='tr_db'>");
    out.print("<th class='db_th'> Prénom </th>");
    out.print("<th class='db_th'> Nom </th>");
    out.print("<th class='db_th'> Delete </th>");
    out.print("</tr>");
    while (users.next()) {
      String id = users.getString("id");
      out.print("<tr>");
      out.print("<td class='db_td'>");
      out.print(users.getString("prenom"));
      out.print("</td>");
      out.print("<td class='db_td'>");
      out.print(users.getString("nom"));
      out.print("</td>");
      out.print("<td class='db_td'>");
      out.print("<a href=delete_data.jsp?id=" + id);
      out.print(">Delete</a>");
      out.print("</td>");
      out.print("</tr>");
    }
    out.print("</tbody>");
    out.print("</table");
    out.print("</div>");
    out.print("<form action='pre_add' method='get'>");
    out.print("<button type='submit' class='right_back_button' name='exit_button'>Back</button>");
    out.print("</form>");
    out.print("</body></html>");
  }
}
