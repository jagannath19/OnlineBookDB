package com.book.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BooksInputs {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		
		String url="jdbc:mysql://localhost:3306/dbtraining";
		String username="root";
		String password="root";
		Connection connection=null;
		Statement statement=null;
		String sql="create table book(Title varchar(50),Author varchar(15),Category varchar(10),BookId integer,Price integer);";
				
		
		try {
			connection=DriverManager.getConnection(url, username, password);
			statement=connection.createStatement();
			boolean val=statement.execute(sql);
			System.out.println(val);
			
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null) {
					connection.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		scn.close();
	}

}





