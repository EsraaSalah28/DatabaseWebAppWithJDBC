package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;

public class StudentDbUtil {
	
	private DataSource datasource;
	
	
 public StudentDbUtil (DataSource thedatasource)
 {
	 
	 datasource=thedatasource;
	 
	 
 }
 
 public List <Student> getStudents() throws Exception
 {
	 List <Student> students = new ArrayList<>();
	 
	 Connection myConn=null;
	 Statement stmt =null;
	 ResultSet myRs=null;
	 try {
	 // get a connection
		 myConn = datasource.getConnection();
	 // create sql statement
		   String sql = "select * from student order by last_name";
           stmt =myConn.createStatement();
	 // execute query
           myRs= stmt.executeQuery(sql);
	 // process result set
	 while (myRs.next())
	 {  int id = myRs.getInt("id");
	   String firstName = myRs.getString("first_name");
	   String lastName = myRs.getString("last_name");
	   String email = myRs.getString("email");

		 Student tempStudent = new Student(id,firstName,lastName,email);
		 
		 students.add(tempStudent);
		 
	 }
	
	 
	 
	 
		 return students; }
	 finally
	 {
		 
		 // close jdbc objects
		 
		 close(myConn,myRs,stmt);
		 
	 }
	 
	
 }

private void close(Connection myConn, ResultSet myRs, Statement stmt) {
	// TODO Auto-generated method stub
	
	try
	{
		if(myRs != null)
		{
			myRs.close();
			
		}
		if(stmt != null)
		{
			stmt.close();
			
		}
		if(myConn != null)
		{
			myConn.close();
			
		}
		
		
		
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	
}
 

}
