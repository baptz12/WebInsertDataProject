package com.bapt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
  {
    HttpSession session = req.getSession();
    session.invalidate();
    res.sendRedirect("login.jsp");
  }
}
