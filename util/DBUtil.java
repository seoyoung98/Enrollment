package com.seoyoung.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	// DB 연결
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost : 127.0.0.1
		String userid = "seoyoung"; String password = "1234"; // 관리자로 로그인하기 
		Connection conn = null; // 연결
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
			
	}
		
	// DB 연결 해제, 자원 반납, Statement, ResultSet 해제
	public static void dbDisconnect(ResultSet rs, Statement st, Connection conn) {
		try { // 거꾸로 써주기 그냥 복사 붙여넣기
			if (rs != null) rs.close(); // rs가 null인데 close하면 안된다. 결과가 있어야 닫는다.
			if (st != null) st.close(); // 통로가 있어야 닫는다.
			if (conn != null) conn.close(); // 연결이 있어야 닫는다.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
