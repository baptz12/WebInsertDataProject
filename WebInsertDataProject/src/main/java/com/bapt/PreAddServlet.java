package com.bapt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreAddServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
  {
    res.sendRedirect("index.jsp");
  }
}
