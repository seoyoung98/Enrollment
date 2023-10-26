package com.seoyoung.dto;
//수강신청 목록을 담을 vo
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ AllArgsConstructor
@ NoArgsConstructor
@Getter @Setter 

public class EnrollListVO {
	private int enroll_num; // 등록 번호
	private int stu_id; // 학생 번호
	private String stu_name; // 학생 이름
	private String sub_id; // 과목 번호
	private String sub_name; // 과목 이름
	private String professor_name; // 교수님 성함
	private String class_loc; // 교실 위치
	private int class_time; // 수업 시간 
	private int sub_credit;
	
	@Override
	public String toString() {
		return "수강 신청 목록 [등록 번호 = " + enroll_num + ", 학생 번호 = " + stu_id + ", 학생 이름 = " + stu_name 
				+ ", 강의명 = " + sub_id + ", 강의명 = " + sub_name + ", 교수명 = " + professor_name + ", 강의실 위치 = " + class_loc
				+ ", 강의 시간 = " + class_time + ", 학점 = " + sub_credit + "]";
	}
	
	
}
