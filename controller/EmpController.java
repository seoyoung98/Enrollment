package com.seoyoung.controller;

import java.util.Scanner;

import com.seoyoung.dto.EmployeeVO;
import com.seoyoung.dto.SubjectVO;
import com.seoyoung.view.UniView;

public class EmpController {
	// 교직원 아이디 비밀번호 입력
	protected EmployeeVO emp_id_password(Scanner sc) {
		System.out.print("아이디 >> ");
		String emp_id = sc.nextLine();
		System.out.print("비밀 번호 >> ");
		int emp_password = Integer.parseInt(sc.nextLine());
		EmployeeVO employeeVO = new EmployeeVO(emp_id, emp_password);
		return employeeVO;
	}
	
	protected void emp_login_complete() {
		UniView.complete_message_print("관리자님, 로그인에 성공하셨습니다.");
		
		UniView.select_menu_print("1. 강의 추가 \n2. 강의 수정 \n3. 강의 삭제 \n4. 강의 조회 \n5. 로그아웃");
		System.out.println();
		System.out.print("원하는 항목의 번호를 입력 >> ");
	}
	
	protected SubjectVO modify_sub(Scanner sc) {
		System.out.println();
		System.out.print("수정하고 싶은 강의 번호 >> ");
		String sub_id = sc.nextLine();
		System.out.println("수정할 정보를 입력해주세요.");
		System.out.print("교수명 >> ");
		String professor_name = sc.nextLine();
		System.out.print("강의실 위치 >> ");
		String class_loc = sc.nextLine();
		System.out.print("강의 시간 >> ");
		int class_time = Integer.parseInt(sc.nextLine());
		SubjectVO subject = new SubjectVO(sub_id, professor_name, class_loc, class_time);
		return subject;
	}
}
