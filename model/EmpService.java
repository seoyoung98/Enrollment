package com.seoyoung.model;

import com.seoyoung.dto.SubjectVO;

public class EmpService {
	EmpDAO dao = new EmpDAO();

	// 교직원
	// 직원 - 강의 추가
	public int insertSubject(SubjectVO subject) {
		// TODO Auto-generated method stub
		return dao.insertSubject(subject);
	}

	// 직원 - 강의 수정
	public int updateSubject(SubjectVO subject) {
		// TODO Auto-generated method stub
		return dao.updateSubject(subject);
	}

	// 직원 - 강의 삭제
	public int deleteSubject(String sub_id) {
		// TODO Auto-generated method stub
		return dao.deleteSubject(sub_id);
	}

}
