package com.seoyoung.dto;
// 학생의 목록을 담을 vo
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ AllArgsConstructor
@ NoArgsConstructor
@ Getter @ Setter 
public class StudentVO {
	private int stu_id; // 학생 번호
	private String stu_name; // 학생 이름
	private String stu_email; // 학생 이메일 -- unique
	private String stu_password; // 학생 비밀번호
	private String stu_address; // 학생 주소
	private String stu_phone; // 학생 전화번호
	
	public StudentVO(String stu_name, String stu_password, String stu_address, String stu_phone, String stu_email) {
		super();
		this.stu_name = stu_name;
		this.stu_email = stu_email;
		this.stu_password = stu_password;
		this.stu_address = stu_address;
		this.stu_phone = stu_phone;
	}

	public StudentVO(String stu_email, String stu_password) {
		super();
		this.stu_email = stu_email;
		this.stu_password = stu_password;
	}

	
}
