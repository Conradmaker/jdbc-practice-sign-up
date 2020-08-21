package com.kh.controller;


import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

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
	    int result=	new MemberDao().insertMember(m);
	    
	    if(result>0) {
	    	new MemberMenu().displaySuccess("회원가입 성공");
	    }else {
	    	new MemberMenu().displayFail("회원가입 실패");
	    }
	}
	
	
	/**
	 * 사용자간의 회원전체조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Member> list = new MemberDao().selectList();
		//조회 결과가 있는지 없는지 판단 한 후 사용자가 보게될 view 지정
		if(list.isEmpty()) { //텅빈 리스트일 경우 --> 조회결과 없음
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {  //뭐라도 조회가 됨
			new MemberMenu().displayMemberData(list);
		}
		
	}
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param userId	=> 사용자가 검색하고자 하는 회원아이디
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m==null) { //검색결과가 있을경우
			new MemberMenu().displayNoData("검색결과가 없다. ");
		}else { //조회되었을경우
			new MemberMenu().displayMember(m);
		}
	}
	
	/**
	 * 사용자의 회원명으로 검색요청시 처리해주는 메소드
	 * @param keyword ->사용자가 검색하고자 하는 회원명 키워드
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) {//결과 없을경우
			new MemberMenu().displayNoData(keyword+"조회결과가 없어요");
		}else {
			new MemberMenu().displayMemberData(list);
		}
	}
	/**
	 * 사용자의 정보 변경 요청을 처리해주는 메소드
	 * @param m -> 변경하고자 하는 회원아이디, 변경할 정보(암호,이메일,전화번호,주소)
	 */
	public void updateMember(Member m) {
		int result = new MemberDao().updateMember(m);
		if(result > 0) {
			new MemberMenu().displaySuccess("회원정보 변경 성공");
		}else {
			new MemberMenu().displayFail("변경실패!");
		}
	}
}
