package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

//View : 사용자가 보게될 시각적인 요소(화면) (입력및 출력)

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);	
	
	//Member Controller 객체 생성(전역에서 바로 요청할 수 있게끔)
	private MemberController  mc = new MemberController();
	
	/**
 * 사용자가 보게 될 첫 화면
 *
 */
	public void mainMenu() {
		int menu;
		while(true) {
			System.out.println("\n==회원관리프로그램==");
			System.out.println("1.회원가입");
			System.out.println("2.회원 전체 조회");
			System.out.println("3.회원 아이디로 검색");
			System.out.println("4.회원이름으로 키워드 검색");
			System.out.println("5.회원정보 변경");
			System.out.println("6.회원탈퇴");
			System.out.println("0.프로그램종료");
			System.out.print("번호 선택");
			
			 menu = sc.nextInt();
			 sc.nextLine();
			 
			 switch(menu) {
			 case 1:insertMember(); break;
			 case 2:mc.selectList(); break;
			 case 3:String userId = inputMemberId(); 
			 		mc.selectByUserId(userId);
			 		break;
			 case 4:// 코드를 줄여보자
			 		mc.selectByUserName(inputMemberName());
			 		break;
			 case 5:updateMember(); break;
			 case 6:break;
			 case 0:System.out.println("프로그램을 종료하겠습니다"); return;
			 default:System.out.println("번호 잘못입력했다."); return;
			 }
		}
	}
	
	//회원가입창
	public void insertMember() {
		System.out.println("\n =====회원가입=====");
		
		System.out.print("아이디:");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호:");
		String userPwd = sc.nextLine();
		
		System.out.print("이름:");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F)");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이:");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일:");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-제오ㅣ):");
		String phone = sc.nextLine();
		
		System.out.print("주소:");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 공백없이):");
		String hobby = sc.nextLine();
		
		//회원가입 요청 (Controller 메소드 호출)
		Member m =new Member(userId,userPwd, userName, gender,age,email,phone, address, hobby);
		
		mc.insertMember(m);
		
	}
	/**
	 * 사용자에게 회원 아이디 입력받은 후 그 입력한 값 반환해주는 메소드
	 * @return  -> 사용자가 입력한 회원아이디
	 */
	public String inputMemberId() {
		System.out.println("회원 아이디 입력: ");
		
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 검색할 회원명 입력받은 후 그 입력된 값 반환해주는 메소드
	 * @return  -> 사용자가 입력한 키워드
	 */
	public String inputMemberName() {
		System.out.println("회원명(키워드) 입력: ");
		
		return sc.nextLine();
	}
	/**
	 * 사용자에게 변경할 정보들 (비밀번호, 이메일, 전화번호, 주소)와
	 * 해당 회원의 아이디 입력받는 화면
	 */
	public void updateMember() {
		System.out.println("\n ====회원정보 변경====");
	
		Member m = new Member();
		m.setUserId(inputMemberId());
		
		System.out.print("변경할 암호: ");
		m.setUserPwd(sc.nextLine());
		
		System.out.print("변경할 이메일: ");
		m.setEmail(sc.nextLine());
		
		System.out.print("변경할 전화번호(-빼고 입력): ");
		m.setPhone(sc.nextLine());
		
		System.out.print("변경할 주소: ");
		m.setAddress(sc.nextLine());
		
		//회원정보 변경 요청! (Controller 메소드 호출)
		mc.updateMember(m);
	}
	
	//------------------------------------
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : "+message);
	}
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : "+message);
	}
	public void displayNoData(String message) {
		System.out.println("\n"+message);
	}
	public void displayMemberData(ArrayList<Member> list) {
		System.out.println("\n == 조회된 데이터는 다음과 같습니다 ==");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	public void displayMember(Member m) {
		System.out.println("\n == 조회된 데이터는 다음과 같습니다 ==");
		System.out.println(m);
		}
}
