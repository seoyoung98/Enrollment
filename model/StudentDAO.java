package com.seoyoung.model;
// execute => 수행 결과로 boolean 타입의 값을 반환, 모든 구문 수행 가능

// executeQuery => 수행 결과로 resultset 객체의 값을 반환, select 구문을 수행
// executeUpdate => 수행 결과로 int 타입의 값을 반환, select을 제외한 구문을 수행 

import java.sql.*;
import java.util.*;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import com.seoyoung.dto.*;
import com.seoyoung.util.*;
import com.seoyoung.view.UniView;

public class StudentDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	int count;

	// 학생
	// 회원가입 - 학생 정보 추가하기
	public int insertStudent(StudentVO student) {
		String sql = "insert into student values (stu_id_seq.nextval, ?, ?, ?, ?, ?)";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, student.getStu_name());
			pst.setString(2, student.getStu_password());
			pst.setString(3, student.getStu_address());
			pst.setString(4, student.getStu_phone());
			pst.setString(5, student.getStu_email());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("이미 존재하는 정보입니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn); // 질문하기
		}
		return count;
	}

	// 로그인 => 아이디 비밀번호 확인하기
	public int login(int id, int password) {
		String sql = "select stu_password from student where stu_id = ?";
		conn = DBUtil.getConnection();
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("stu_id");
			}

		} catch (SQLException e) {
			System.out.println("학생의 번호가 존재하지 않습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return result;
	}

	// 학생 로그인
	public boolean studentLogin(String email, String password) {
		boolean result = false;

		String sql = "select stu_password from student where stu_email = ?";
		System.out.println(email);
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();

			if (rs.next()) {
				result = password.equals(rs.getString("stu_password"));
			}
		} catch (SQLException e) {
			UniView.fail_print("로그인에 실패하셨습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}

		return result;
	}

	// 이메일 찾기
	public String findEmail(int stu_id) {
		String result = "";
		String sql = "select stu_email from student where stu_id = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, stu_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getString("stu_email");
			}
		} catch (SQLException e) {
			System.out.println("학생의 정보를 찾을 수 없습니다. 다시 입력해주세요.");
		} catch (Exception e) {
			System.out.println("학생의 정보를 찾을 수 없습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}

		return result;
	}

	// 비밀번호 찾기
	public String findPassword(String stu_email) {
		String result = "";
		String sql = "select stu_password from student where stu_email = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu_email);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getString("stu_password");
			}
		} catch (SQLException e) {
			System.out.println("이메일이 존재하지 않습니다. 다시 입력해주세요.");
		} catch (Exception e) {
			System.out.println("이메일이 존재하지 않습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}

		return result;
	}

	// 이메일을 기준으로 로그인 시 name 찾기
	public String studentLoginName(String stu_email) {

		String sql = "select stu_name from student where stu_email = ?";
		conn = DBUtil.getConnection();
		String result = "";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu_email);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getString("stu_name");
			}
		} catch (SQLException e) {
			System.out.println("학생의 이메일이 존재하지 않습니다. 다시 입력해주세요.");
		} // 통로 완료
		finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return result;

	}

	// 학생의 학생 번호 받아오기
	public int getStuId(String stu_email) {
		String sql = "select stu_id from student where stu_email = ?";

		conn = DBUtil.getConnection();
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu_email);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("stu_id");
			}

		} catch (SQLException e) {
			System.out.println("학생의 이메일이 존재하지 않습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return result;
	}

	// 수강 신청 가능한 강의 목록 불러오기
	public List<SubjectVO> selectSubjectVO() {
		List<SubjectVO> subject_List = new ArrayList<>();
		String sql = "select * from subject order by major desc";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			// 칼럼이 엄청 많을 때는 어떻게 돌려야 할까
			while (rs.next()) {
				// rs의 주소를 넘겨서 만들어서 준다?
				// reset에서 읽어서 vo 만들기
				SubjectVO subject = make_subList(rs);
				subject_List.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // 통로 완료
		finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return subject_List;
	}

	private StudentVO make_stuList(ResultSet rs) throws SQLException {
		StudentVO list = new StudentVO();
		list.setStu_id(rs.getInt(1));
		list.setStu_name("stu_name");
		list.setStu_email("stu_email");
		list.setStu_password("stu_password");
		list.setStu_address("stu_address");
		list.setStu_phone("stu_phone");
		return list;
	}

	private SubjectVO make_subList(ResultSet rs) throws SQLException {
		SubjectVO list = new SubjectVO();
		list.setSub_id(rs.getString("sub_id"));
		list.setMajor(rs.getString("major"));
		list.setSub_name(rs.getString("sub_name"));
		list.setSub_credit(rs.getInt("sub_credit"));
		list.setProfessor_name(rs.getString("professor_name"));
		list.setClass_loc(rs.getString("class_loc"));
		list.setClass_time(rs.getInt("class_time"));
		list.setCount_stu(rs.getInt("count_stu"));
		return list;
	}

	// email로 id 가져오기
	public int selectStuid(String stu_email) {
		String sql = "select stu_id from student where stu_email = ?";

		conn = DBUtil.getConnection();
		int result = 0;

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu_email);
			rs = pst.executeQuery();
			if (rs.next()) {
				result += rs.getInt("stu_id");
			}
		} catch (SQLException e) {
			System.out.println("학생의 이메일이 존재하지 않습니다. 다시 입력해주세요.");
		} // 통로 완료
		finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return result;
	}

	// 수강신청하기
	public int insertEnroll(int getId, String sub_id) {
//		String sql = "select st.*, su.* from student st join enrollment e on (st.stu_id = e.stuid) join subject su on su.sub_id = e.sub_id where stu_id = ?";
		String sql = "insert into Enrollment values (enroll_seq.nextval, ?, ?)";
		conn = DBUtil.getConnection();
		int count = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, getId);
			pst.setString(2, sub_id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			UniView.fail_print("이미 수강신청한 과목입니다. 다시 입력해주세요.");

		} // 통로 완료
		finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}

	// 수강신청 - 수강 신청 취소하기
	public int deleteEnroll(int enroll_num) {
		String sql = "delete from enrollment where enroll_num = ?";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, enroll_num);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			UniView.fail_print("일치하지 않은 번호입니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}

	public List<EnrollListVO> enroll_join(int getId, String sub_id) {
		List<EnrollListVO> enrollList = new ArrayList<>();
		String sql = "select e.enroll_num, st.*, su.* from student st join enrollment e on (st.stu_id = e.stu_id) join subject su on su.sub_id = e.sub_id where st.stu_id = ? and su.sub_id = ?";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, getId);
			pst.setString(2, sub_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				EnrollListVO enroll = make_Enroll(rs);
				enrollList.add(enroll);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return enrollList;
	}

	private EnrollListVO make_Enroll(ResultSet rs) throws SQLException {
		EnrollListVO list = new EnrollListVO();
		list.setEnroll_num(rs.getInt("enroll_num"));
		list.setStu_id(rs.getInt("stu_id"));
		list.setStu_name(rs.getString("stu_name"));
		list.setSub_id(rs.getString("sub_id"));
		list.setSub_name(rs.getString("sub_name"));
		list.setProfessor_name(rs.getString("professor_name"));
		list.setClass_loc(rs.getString("class_loc"));
		list.setClass_time(rs.getInt("class_time"));
		list.setSub_credit(rs.getInt("sub_credit"));
		return list;
	}

	// 수강신청 - 내역 조회
	public List<EnrollListVO> enroll_list(int stu_id) {
		List<EnrollListVO> enrollList = new ArrayList<>();
		String sql = "select e.enroll_num, st.*, su.* from student st join enrollment e on (st.stu_id = e.stu_id) join subject su on (su.sub_id = e.sub_id) where st.stu_id = ?";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, stu_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				EnrollListVO enroll = make_Enroll(rs);
				enrollList.add(enroll);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return enrollList;
	}

	public int updatePassword(String new_password, String stu_email) {
		String sql = "update student set stu_password = ? where stu_email = ?";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, new_password);
			pst.setString(2, stu_email);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			UniView.fail_print("비밀번호 수정에 실패하였습니다. 다시 입력해주세요.");
		} finally {
			DBUtil.dbDisconnect(rs, pst, conn);
		}
		return count;
	}

}
