package com.soft;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JDBCconnection extends GenericServlet {

	Connection con = null;
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","Jaigajanan@98");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		try {
			
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			
			String Name = req.getParameter("Name");
			String Department = req.getParameter("Department");
			String Address = req.getParameter("Address");
			String Salary = req.getParameter("Salary");
			
			PreparedStatement ps = con.prepareStatement("insert into servlet.servlet1 values (?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, Department);
			ps.setString(3, Address);
			ps.setString(4, Salary);
			
			
			int k = ps.executeUpdate();
			
			if(k>0) {
				pw.println("Data Stored in DB successfully");
			}else {
				pw.println("Error in Storing Data");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
