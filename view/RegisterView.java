package com.seoyoung.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.seoyoung.dto.*;

// 학생의 회원가입 뷰 
public class RegisterView {

	public StudentVO StuRegister(Scanner sc) {
		System.out.println("-----------------------------------------");
		System.out.println("학생 회원가입 창입니다.");
		System.out.println("-----------------------------------------");
		System.out.print("이름 >> ");
		String stu_name = sc.next();
		System.out.print("이메일(아이디) >> ");
		String stu_email = checkEmail(sc);
		System.out.print("비밀 번호 (9 ~ 12자리) >> ");
		String stu_password = checkPassword(sc);
		System.out.print("주소 (서울, 경기도, 전라도, 경상도, 강원도, 충청도, 제주도) >> ");
		String stu_address = checkAddress(sc);
		System.out.print("전화 번호 [###-####-####]>> ");
		String stu_phone = checkPhone(sc);
		System.out.println("-----------------------------------------");
		StudentVO studentVO = new StudentVO(stu_name, stu_password, stu_address, stu_phone, stu_email);
		return studentVO;
	}

	// 이메일 확인 => 추가 하기 
	// 만약 있는 이메일이라면 다시 입력받기 
	private String checkEmail(Scanner sc) {
		String email;
		boolean flag;
		String emailpattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		sc.nextLine(); // 이상하면 지워라 
		do {
			email = sc.nextLine();
			flag = Pattern.matches(emailpattern, email);
			if (!flag) {
				System.out.println("입력 형식에 맞게 작성해 주세요.");
				System.out.print("이메일 >> ");
			}

		} while (!flag);
		return email;
	}

	// 주소 확인 => 확인
	private String checkAddress(Scanner sc) {
		String address;
		boolean flag;
		// 리스트 안에 있으면
		List<String> add_list = Arrays.asList("서울", "경기도", "전라도", "경상도", "강원도", "충청도", "제주도");
		do {
			address = sc.next();
			flag = add_list.contains(address);
			if (!flag) {
				System.out.println("항목에 있는 주소를 입력해주세요.");
			}
		} while (!flag);

		return address;
	}

	// 핸드폰 번호 확인 => 확인
	private String checkPhone(Scanner sc) {
		String phone;
		boolean flag;
		String phonepattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
		do {
			phone = sc.next();
			flag = Pattern.matches(phonepattern, phone);
			if (!flag) {
				System.out.print("형식에 맞게 전화번호를 입력해주세요. [###-####-####] >> ");
			}
		} while (!flag);
		return phone;
	}

	// 비밀번호 확인 (영어(대소문자), 숫자9 ~ 16자리인지 확인) => 다시
	private String checkPassword(Scanner sc) {
		String password = "1234";
		boolean flag;
		String passwordpattern = "^[A-Za-z0-9]{9,16}$";

		do {
			password = sc.next();
			flag = Pattern.matches(passwordpattern, password);
			if (!flag) {
				System.out.println("형식에 맞게 비밀번호를 입력해주세요. (9 ~ 16 자리 입력)");
			}
		} while (!flag);
		return password;
	}
}
