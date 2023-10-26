package com.seoyoung.controller;

import java.util.Scanner;

import com.seoyoung.dto.StudentVO;
import com.seoyoung.view.UniView;

public class StudentController {
	protected void welcomeStudent() {
		UniView.menu_print("학생 창 입니다.");
		System.out.println("1. 회원가입 \n2. 로그인");
		System.out.print("입력 >> ");
	}
	
	protected void select_loginStudent() {
		UniView.menu_print("학생 로그인 창 입니다.");
		System.out.println("1. 로그인 \n2. 이메일 찾기 \n3. 비밀번호 찾기");
		System.out.println();
		System.out.print("선택 >> ");
	}
	
	protected StudentVO loginStudent(Scanner sc) {
		System.out.print("이메일을 입력하세요. >> ");
		String stu_email = sc.nextLine();
		System.out.println();
		System.out.print("비밀번호를 입력하세요. >> ");
		String stu_password = sc.nextLine();
		StudentVO studentVO = new StudentVO(stu_email, stu_password);
		return studentVO;
	}
}
