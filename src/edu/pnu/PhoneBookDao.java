package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhoneBookDao {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(url, "root", "tiger");
		boolean flag = true;
		while (flag) {
			System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/[N]ative/[Q]uit:");
			char c = sc.next().toUpperCase().charAt(0);
			switch (c) {
			case 'I':
				insertPhonebook(con);
				break;
//			case 'U':
//				updatePhonebook(con);
//				break;
//			case 'D':
//				deletePhonebook(con);
//				break;
//			case 'S':
//				selectAllPhonebook(con);
//				break;
//			case 'N':
//				nativeQuery(con);
//				break;
//			case 'Q':
//				flag = false;
//				break;
			}
		}
		System.out.println("Bye~");
	}
	


	private static void insertPhonebook(Connection con) throws SQLException {
		// 그냥엔터치는 것에 대한 대비
		String sql = "insert into phonebook(name, mobile, home, id, company, email) values(?,?,?,?,?,?)";
		try (PreparedStatement psmt = con.prepareStatement(sql)) {
		    System.out.print("이름: ");
		    String name = sc.next();

		    System.out.print("휴대폰 번호: ");
		    String mobile = sc.next();

		    System.out.print("home: ");
		    String home = sc.next();

		    System.out.print("id: ");
		    int id = sc.nextInt();

		    System.out.print("company: ");
		    String company = sc.next();

		    System.out.print("email: ");
		    String email = sc.next();

		    psmt.setString(1, name);
		    psmt.setString(2, mobile);
		    psmt.setString(3, home);
		    psmt.setInt(4, id);
		    psmt.setString(5, company);
		    psmt.setString(6, email);

		    int cnt = psmt.executeUpdate();
		    System.out.println(cnt + "건이 입력되었습니다.");
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
//	private static void updatePhonebook(Connection con) throws SQLException {
//	    String sql = "UPDATE phonebook SET name = ?, mobile = ?, home = ?, email = ? WHERE id = ?";
//	    
//	    try (PreparedStatement psmt = con.prepareStatement(sql)) {
//	        System.out.print("id: ");
//	        int id = sc.nextInt();
//	        sc.nextLine(); // 개행 제거
//	        
//	        System.out.print("name: ");
//	        String name = sc.nextLine();
//	        
//	        System.out.print("mobile: ");
//	        String mobile = sc.nextLine();
//	        
//	        System.out.print("home: ");
//	        String home = sc.nextLine();
//	        
//	        System.out.print("email: ");
//	        String email = sc.nextLine();
//
//	        psmt.setString(1, name);
//	        psmt.setString(2, mobile);
//	        psmt.setString(3, home);
//	        psmt.setString(4, email);
//	        psmt.setInt(5, id);
//
//	        int cnt = psmt.executeUpdate();
//	        System.out.println(cnt + "건이 수정되었습니다.");
//	    }
//	}
//
//
//	private static void deletePhonebook(Connection con) throws SQLException {
//	    String sql = "delete from phonebook where id=?";
//	    try (PreparedStatement psmt = con.prepareStatement(sql)) {
//	        System.out.print("id: ");
//	        int id = sc.nextInt();
//	        psmt.setInt(1, id);
//	        
//
//	        int cnt = psmt.executeUpdate();  // 여기서 한 번만 실행
//	        System.out.println(cnt + "건이 삭제되었습니다.");
//	    }
//	}
//
//	
//	private static void selectAllPhonebook(Connection con) throws SQLException {
//	    String sql = "select * from phonebook where id > ?";
//	    try (PreparedStatement psmt = con.prepareStatement(sql)) {
//	        System.out.print("id : ");
//	        int id = sc.nextInt();
//	        sc.nextLine();
//	        
//	        psmt.setInt(1, id);
//	        int cnt =0;
//
//	        try (ResultSet rs = psmt.executeQuery()) {
//	    
//	            while (rs.next()) {
//	            	cnt ++ ;
//	                int userId = rs.getInt("id");
//	                String name = rs.getString("name");
//	                String mobile = rs.getString("mobile");
//	                String home = rs.getString("home");
//	                String company = rs.getString("company");
//	                String email = rs.getString("email");
//
//	                System.out.printf("%d | %s | %s | %s | %s | %s%n", userId, name, mobile, home, company, email);
//	            }
//	           System.out.println(cnt + "건이 조회되었습니다");
//	        }
//	    }
//	}
//
//	//next 단어, nextLine 문장
//	private static void nativeQuery(Connection con) {
//	    // 사용자에게 실행할 SQL문을 입력받아서 대문자로 설정하기.
//	    System.out.print("SQL문: ");
//	    sc.nextLine();
//	    String sql = sc.nextLine().toUpperCase();
//	    
//	    int cnt =0;
//
//	    try (Statement st = con.createStatement()){
//	    	// SQL문 시작이 SELECT이면, SELECT인지 아닌지 판별 (SELECT면 executeQuery, 아니면 executeUpdate 사용)
//	    	if(sql.startsWith("SELECLT")) {
//		        // SELECT일 경우:
//		        ResultSet rs = psmt.executeQuery();
//		        while(rs.next()) {
//		        // - while(rs.next()) 돌면서 컬럼 값 출력
//		        
//		       
//		        int cnt = psmt.executeUpdate();
//			    System.out.println(cnt + "건이 수정되었습니다.");
//		    	st.executeQuery(sql);
//	    	} else {
//
//		        // INSERT, UPDATE, DELETE일 경우:
//		        // - int cnt = psmt.executeUpdate();
//		    	st.executeUpdate(sql);
//	    		// - 처리된 행 수 출력
//	    	}
//	    } catch (Exception e) {
//	        e.printStackTrace(); // 예외 발생 시 출력
//	    }
//	}

}

// ResultSet rs - st.executeQuery("앙ㄹㄴ")
// ResultSetMetaData meta = rs.getMetadata(); 인데스로 가져옴
// int count = meta.getColumnCount();
// While(rs.next()){for(int i =0; i<count; i++)sysout(rs.getString(i))
