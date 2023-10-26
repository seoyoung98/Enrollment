package com.seoyoung.dto;
// 과목을 담을 VO 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ AllArgsConstructor
@ NoArgsConstructor
@Getter @Setter 
public class SubjectVO {
	private String sub_id; // 과목 번호
	private String major; // 전공
	private String sub_name; // 과목 이름
	private int sub_credit; // 학점 
	private String professor_name; // 교수님 성함
	private String class_loc; // 교실 위치
	private int class_time; // 수업 시간 
	private int count_stu; // 수강 신청한 학생 수
	
	// 강의 수정 시 필요한 생성자 
	public SubjectVO(String sub_id, String professor_name, String class_loc, int class_time) {
		super();
		this.sub_id = sub_id;
		this.professor_name = professor_name;
		this.class_loc = class_loc;
		this.class_time = class_time;
	}

	@Override
	public String toString() {
		return "강의 정보 [과목 번호 = " + sub_id + ", 전공 = " + major + ", 강의명 = " + sub_name + ", 교수명 = " + professor_name + ", 학점 = "
				+ sub_credit + ", 강의실 위치 = " + class_loc + ", 수업 시간 = "
				+ class_time + ", 학생 수 = " + count_stu + "]";
	}
	
	
}
