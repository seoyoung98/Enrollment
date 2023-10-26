package com.seoyoung.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.seoyoung.dto.SubjectVO;
import com.seoyoung.util.DBUtil;
import com.seoyoung.view.UniView;

public class EmpDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	int count;

	// 교직원
	// 교직원 - 강의 추가
	public int insertSubject(SubjectVO subject) {
		String sql = "insert into subject(sub_id, major, sub_name, sub_credit, professor_name, class_loc, class_time) values (?, ?, ?, ?, ?, ?, ?)";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, subject.getSub_id());
			pst.setString(2, subject.getMajor());
			pst.setString(3, subject.getSub_name());
			pst.setInt(4, subject.getSub_credit());
			pst.setString(5, subject.getProfessor_name());
			pst.setString(6, subject.getClass_loc());
			pst.setInt(7, subject.getClass_time());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			UniView.fail_print("이미 존재하는 강의입니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}

	// 강의 수정 - 교수님, 강의실 위치, 강의 시간 - 강의 번호를 기준으로
	public int updateSubject(SubjectVO subject) {
		String sql = "update subject set professor_name = ?, class_loc = ?, class_time = ? where sub_id = ?";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(4, subject.getSub_id());
			pst.setString(1, subject.getProfessor_name());
			pst.setString(2, subject.getClass_loc());
			pst.setInt(3, subject.getClass_time());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			UniView.fail_print("존재하지 않는 강의입니다. 다시 입력해주세요.");
//				e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}

	// 교직원 - sub_id를 기준으로 강의 삭제
	public int deleteSubject(String sub_id) {
		String sql = "delete from subject where sub_id = ?";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sub_id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			UniView.fail_print("강의가 항목에 존재하지 않습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}
}
