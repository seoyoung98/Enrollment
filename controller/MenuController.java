package com.seoyoung.controller;

import java.util.Scanner;

import com.seoyoung.dto.EmployeeVO;
import com.seoyoung.dto.SubjectVO;
import com.seoyoung.view.UniView;

public class MenuController {
	// 학생, 교직원 선택 => 사용 완료 
	protected void emp_stu_select() {
		System.out.println("=========================================");
		System.out.println();
		System.out.println("수강신청 사이트에 오신 것을 환영합니다.");
		System.out.println();
		System.out.println("=========================================");
		System.out.println("1. 교직원 \n2. 학생 \n3. 프로그램 종료");
		System.out.println("-----------------------------------------");
		System.out.print("사용자 유형을 선택하세요. >> ");
	}
		
	// 학생 수강신청 메뉴
	protected void stu_login_menu() {
		System.out.println("=========================================");
		System.out.println("1. 수강신청 \n2. 수강신청 취소 \n3. 수강신청 내역 조회 \n4. 로그아웃");
		System.out.println("=========================================");
		System.out.print("목록을 입력하세요. >> ");
	}

	// 교직원이 추가할 강의 정보
	protected SubjectVO insert_sub_menu(Scanner sc) {
		UniView.show_menu_print("추가할 강의의 정보를 입력하세요.");
		System.out.print("강의 번호(영,숫자 10자리) >> ");
		String sub_id = sc.nextLine();
		System.out.print("전공명 >> ");
		String major = sc.nextLine();
		System.out.print("강좌명 >> ");
		String sub_name = sc.nextLine();
		System.out.print("학점 >> ");
		int sub_credit = Integer.parseInt(sc.nextLine());
		System.out.print("교수명 >> ");
		String professor_name = sc.nextLine();
		System.out.print("강의실 위치 >> ");
		String class_loc = sc.nextLine();
		System.out.print("강의 시간 >> ");
		int class_time = Integer.parseInt(sc.nextLine());
		SubjectVO subject = new SubjectVO(sub_id, major, sub_name, sub_credit, professor_name, class_loc, class_time,
				0);
		return subject;
	}

	// 로그인 완료 메세지
	public void studentLoginComplete(String getName) {
		System.out.println();
		System.out.println(getName + " 학생 로그인이 완료되었습니다.");
		System.out.println();
		System.out.println("안녕하세요. " + getName + " 학생 반갑습니다.");
	}


}
