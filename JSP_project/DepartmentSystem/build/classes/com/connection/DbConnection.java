package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbConnection {
   public DbConnection() {
   }

   public static Connection takeConnection() {
      Connection con = null;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/department";
         String user = "root";
         String pass = "root";
         con = DriverManager.getConnection(url, user, pass);
         con.setAutoCommit(true);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return con;
   }

   public static boolean verifyLogin() {
      boolean status = false;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/dept";
         String user = "root";
         String pass = "root";
         Connection con = DriverManager.getConnection(url, user, pass);
         status = true;
         con.setAutoCommit(true);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return status;
   }

   public static void insertData(String title, String desc, String raiseFor, String user, String date) {
      try {
         String pending = "Pending";
         Connection con = takeConnection();
         String query = "insert into approval(title , description , request_dept ,request_by , request_date,status) values(?,?,?,?,?,?)";
         PreparedStatement ps = con.prepareStatement(query);
         ps.setString(1, title);
         ps.setString(2, desc);
         ps.setString(3, raiseFor);
         ps.setString(4, user);
         ps.setString(5, date);
         ps.setString(6, pending);
         ps.executeUpdate();
         con.close();
      } catch (Exception var9) {
         var9.printStackTrace();
      }

   }

   public static void insertRemData(String user, String date, String id) {
      try {
         Connection con = takeConnection();
         String query = "update approval set approval_person =? , approval_date = ? where request_id =" + id;
         PreparedStatement ps = con.prepareStatement(query);
         ps.setString(1, user);
         ps.setString(2, date);
         ps.executeUpdate();
         con.close();
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }
}