package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// static Connection getConnection(String url, String user, String password)
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "tiger");
			System.out.println("연결 성공");
			con.close();
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
	}
}
