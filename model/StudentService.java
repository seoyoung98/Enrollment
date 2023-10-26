package com.seoyoung.model;

import java.util.List;

import com.seoyoung.dto.*;

public class StudentService {
	StudentDAO dao = new StudentDAO();

	// 학생
	// 학생 로그인 -> 학생 로그인에 사용
	public boolean studentLogin(StudentVO student) {
		return dao.studentLogin(student.getStu_email(), student.getStu_password());
	}

	// 학생 정보 추가
	public int insertStudent(StudentVO student) {
		return dao.insertStudent(student);
	}

	// 강의 목록 가져오기
	public List<SubjectVO> selectSubjectVO() {
		return dao.selectSubjectVO();
	}

	// 학생 이메일 찾기
	public String findEmail(int stu_id) {
		String stu_email = dao.findEmail(stu_id);
		if (stu_email.isEmpty()) {
			return "";
		}
		return stu_email;
	}

	// 학생 비밀번호 찾기
	public String findPassword(String stu_email) {
		String stu_password = dao.findPassword(stu_email);
		if (stu_password.isEmpty()) {
			return "";
		}
		return stu_password;
	}

	public String studentLoginName(String stu_email) {
		return dao.studentLoginName(stu_email);
	}

	public int selectStuid(String stu_email) {
		return dao.selectStuid(stu_email);
	}

	// 이메일을 기준으로 학생 전체 정보 가져오기
	public int getStuId(String stu_email) {
		// TODO Auto-generated method stub
		return dao.getStuId(stu_email);
	}

	public int insertEnroll(int getId, String sub_id) {

		return dao.insertEnroll(getId, sub_id);
	}

	//
	public int deleteEnroll(int enroll_num) {
		return dao.deleteEnroll(enroll_num);
	}

	public List<EnrollListVO> enroll_join(int getId, String sub_id) {
		// TODO Auto-generated method stub
		return dao.enroll_join(getId, sub_id);
	}

	public List<EnrollListVO> enroll_list(int stu_id) {
		// TODO Auto-generated method stub
		return dao.enroll_list(stu_id);
	}

	// 비밀번호 수정
	public int updatePassword(String new_password, String stu_email) {
		// TODO Auto-generated method stub
		return dao.updatePassword(new_password, stu_email);
	}

	// 일단 값을 넣어야 한다.

}
