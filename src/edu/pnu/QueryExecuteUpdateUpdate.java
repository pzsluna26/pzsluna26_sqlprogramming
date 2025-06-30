package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class QueryExecuteUpdateUpdate {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		PreparedStatement psmt = null;
		
		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. DB 연결 객체 생성
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			con = DriverManager.getConnection(url, "root", "tiger");
			
//			st = con.createStatement();
//			String name = "홍길동";
//			String mobile = "010-1234-5678";
//			String sql = "insert into phonebook(name,mobile)values ";
//			Statement stmt = con.createStatement();
//			int cnt = stmt.executeUpdate(sql+String.format("('%s','%s')", name, mobile));
// => integer 리턴 = 1
			
			// preparedstatement 사용
//			String sql = "insert into phonebook(name, mobile) values (?, ?)";
//	
//            psmt = con.prepareStatement(sql);
//
//            psmt.setString(1, name);
//            psmt.setString(2, mobile);
//            psmt.executeUpdate();
//            int cnt = psmt.executeUpdate();
			
			String sql = "update phonebook set home=? where id=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, home);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			
//            
//            System.out.println(psmt);
//            System.out.println(cnt + "건이 입력 되었습니다.");

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
