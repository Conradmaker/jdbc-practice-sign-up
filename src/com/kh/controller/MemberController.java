package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

/**
 * @author user2
 * Controller : view를 통해서 요청한 기능 처리하는 담당
 *				해당 메소드로 전달된 데이터 가공처리한 후 Dao로 전달 (Dao메소드 호출)
 *				DAO로부터 반환받은 결과에 따라 성공 또는 실패인지의 View(응답할 화면)을 결정한다.(View매소드 호출) 
 */
public class MemberController {
	/**
	 * 사용자의 회원가입 요청을 처리해주는 매소드
	 * @param m => 사용자가 입력한 정보들이 잔뜩 담겨있는 Member 객체
	 */
	public void insertMember(Member m) {
		new MemberDao().insertMember(m);
	}
	
}