package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class QueryExecuteUpdateDelete {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. DB 연결 객체 생성
            String url = "jdbc:mysql://localhost:3306/myfirstdb";
            con = DriverManager.getConnection(url, "root", "tiger");

            // 사용자로부터 삭제할 ID 입력 받기
            System.out.print("삭제할 ID를 입력하세요: ");
            int id = scanner.nextInt();

            // 3. SQL 준비 및 실행
            String sql = "DELETE FROM phonebook WHERE id = ?";
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, id);

            int cnt = psmt.executeUpdate();  // 삭제된 행의 수 반환
            System.out.println(cnt + "건이 삭제되었습니다.");

        } catch (Exception e) {
            System.out.println("연결 또는 실행 실패: " + e.getMessage());
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (con != null) con.close();
                scanner.close();
            } catch (Exception e) {
                // 무시
            }
        }
    }
}
//메서드구현 케이스