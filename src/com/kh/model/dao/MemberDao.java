package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.model.vo.Member;

public class MemberDao {
	
	/*
	 *  JDBC용 객체
	 *  -Connection : DB의 연결정보를 담고있는 객체
	 *  [Prepared]Statement: DB에 SQL문 전달해서 실행하고 그 결과를 받아내는 객체
	 *  -ResultSet : SELECT 문 수행 후 조회된 결과들이 담겨있는 객체
	 *  
	 *  JDBC 처리순서 
	 *  1. jdbc driver등록: 해당 DBMS 가 제공하는 클래스 등록
	 *  2.Connection 생성 : 연결하고자 하는 DB정보를 입력해서 DB와 연결하면서 생성
	 *  3.Statement 생성 : Connection 객체를 이용해서 생성 (sql문 실행 및 결과받는 객체)
	 *  4.sql문 전달하면서 실행 :Statement객체를 이용해서 sql문 실행
	 *  5.결과받기   : 바로 결과 받기
	 *  		> SELECT문 -> ResultSet객체 (조회된 데이터들이 담겨있음)
	 *  		> DML문      -> int (처리된 행의 객수)
	 *  6_1)ResultSet에 담겨있는 데이터들 하나씩 하나씩 뽑아서 vo객체에 담기
	 *  6_2) 트랜잭션 처리 (성공이면 commit, 실패면 rollback)
	 *  
	 *  7) 다 쓴 JDBC 객체들 반드시 자원반밥(close) -->생성역순으로
	 *  
	 */
	
	/**
	 * 사용자가 입력한 값들로 insert문 실행하는 메소드
	 * @param m -> 사용자가 입력한 값들이 잔뜩 담겨있는 Member객체
	 * @return 
	 */
	public int insertMember(Member m) {
		//필요한 변수를 먼저 셋팅
		int result = 0;//처리된 결과(처리된 행 수)를 받아줄 변수
		Connection conn = null; //DB연결정보를 담는 객체
		Statement stmt = null; //SQL문 실행 후 결과를 받는 객체
		
		//실행할 sql문(완성형태로) -> ;없어야함
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,"
				+ "'"+m.getUserId()+"',"
				+ "'"+m.getUserPwd()+"',"
				+ "'"+m.getUserName() +"',"
				+ "'"+m.getGender()+"',"
				+     m.getAge()+","
				+ "'"+m.getEmail()+"',"
				+ "'"+m.getPhone()+"',"
				+ "'"+m.getAddress()+"',"
				+ "'"+m.getHobby()+"', SYSDATE)";
		//System.out.print(sql);
		try {
			//1.jdbcdriver등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 객체 생성 (DB에 연결 -> url, 계정명, 계정비밀번호
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			//3.Statement 객체 생성
			stmt = conn.createStatement();
			
			//4,5 . DB에 SQL문 전달 하면서 실행 후 결과 받기 (처리된 행 수)
			result = stmt.executeUpdate(sql);
			
			//6_2 . 트랜잭션
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//7.반납 
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
}
