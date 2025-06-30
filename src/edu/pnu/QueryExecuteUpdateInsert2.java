package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class QueryExecuteUpdateInsert2 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. DB 연결 객체 생성
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			con = DriverManager.getConnection(url, "root", "tiger");
			
			st = con.createStatement();

			String name = "홍길동";
			String mobile = "010-1234-5678";
			int cnt = st.executeUpdate(String.format("insert into phonebook(name,mobile) values ('%s','%s')", name, mobile));
            
			System.out.println(cnt + "건이 입력 되었습니다.");
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		} finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}
	}
}
