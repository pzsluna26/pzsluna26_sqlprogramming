package edu.pnu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueryStatement {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    	try {
    	// JDBC 드라이버 로딩
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	// 데이터베이스 연결 객체 생성
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "tiger");
    	
		while (true) {
			System.out.println("1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요. (City)");
			System.out.println("2. 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요. (City)");
			System.out.println("3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Country.Continent)");
			System.out.println("4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로 검색해서 출력\r\n"
					+ "하세요. (Country.SurfaceArea)");
			System.out.println("5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’, City)");
			System.out.println("6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 출력하세요. (예:'Spanish’, CountryLanguage)");
			System.out.println("7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.");
			System.out.println("0.Exit");
			System.out.println("-".repeat(50));
			System.out.print("Select:");
			int n = sc.nextInt(); //질의번호 
			if(n==0)
				break;
			switch(n) {
			case 1: method1(con); break;
			case 2: method2(con); break;
			case 3: method3(con); break;
			case 4: method4(con); break;
			
			}
			System.out.println("=".repeat(80));
		}
			System.out.println("Done");
		}catch(Exception e) {
			System.out.println("");
		}
		
	}
    
    //1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요. (City)
	private static void method1(Connection con) {
		Statement st = null;
		ResultSet rs = null;
	
		try {
			// 몇 명 이상인 도시를 찾을까요? 입력헤주세요
			System.out.print("인구수 입력: ");
			int population = sc.nextInt();

			// 질의 객체 생성
			st = con.createStatement();
			
			// 질의 객체를 이용해서 질의 (SQL)
			rs = st.executeQuery("select id, name, countrycode, district, population "
					+ "from city where population > " + population);

			while (rs.next()) {
				System.out.print(rs.getInt("id") + ",");
				System.out.print(rs.getString("name") + ",");
				System.out.print(rs.getString("countrycode") + ",");
				System.out.print(rs.getString("district") + ",");
				System.out.print(rs.getInt("population") + "\n");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null) st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/*
	 * 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요. (City)*/

	
	private static void method2(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.println("국가코드 입력: ");
			String code = sc.next().toUpperCase();
		
			// 질의 객체 생성
			st = con.createStatement();
						
			// 질의 객체를 이용해서 질의 (SQL)
			rs = st.executeQuery("select name, population from city where countrycode='" + code + "'");
			//select name, population from city where countrycode='AFG'
			
			while (rs.next()) {
				System.out.print(rs.getString("name") + ",");
				System.out.print(rs.getString("countrycode") + ",");
				System.out.print(rs.getInt("population") + "\n");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null) st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}		
	}
	
	//대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Country.Continent)
	private static void method3(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.println("대륙이름 입력 : ");
			String Continent = sc.next();
	
		
			// 질의 객체 생성
			st = con.createStatement();
						
			// 질의 객체를 이용해서 질의 (SQL)
			rs = st.executeQuery("select country.name from country where continent='"+Continent+"'");
			
			
			while (rs.next()) {
				
				System.out.print(rs.getString("name") + ",");
				
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null) st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}		
	}
	
	//넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로 검색해서 출력하세요. (Country.SurfaceArea)
	private static void method4(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.println("대륙이름 입력 : ");
			int SurfaceArea = sc.nextInt();
	
		
			// 질의 객체 생성
			st = con.createStatement();
						
			// 질의 객체를 이용해서 질의 (SQL)
			rs = st.executeQuery("select country.name, SurfaceArea from country where "+ SurfaceArea 
					+"> 1000000 order by country.name, SurfaceArea asc");
			
			
			while (rs.next()) {
				
				System.out.print(rs.getString("country.name") + ",");
				System.out.print(rs.getString("SurfaceArea") + "\n");
				
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null) st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}		
	}
}

