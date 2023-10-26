package com.seoyoung.controller;

import java.util.List;
import java.util.Scanner;

import com.seoyoung.dto.EmployeeVO;
import com.seoyoung.dto.EnrollListVO;
import com.seoyoung.dto.StudentVO;
import com.seoyoung.dto.SubjectVO;
import com.seoyoung.model.EmpService;
import com.seoyoung.model.StudentService;
import com.seoyoung.view.RegisterView;
import com.seoyoung.view.UniView;

public class UniController {
	static Scanner sc = new Scanner(System.in);
	public static int stu_id_sequence = 20230001;
	private static RegisterView register = new RegisterView();
	private static StudentService sservice = new StudentService();
	private static EmpService eservice = new EmpService();

	public static void main(String[] args) {
		MenuController menuController = new MenuController();
		EmpController empController = new EmpController();
		StudentController studentController = new StudentController();
		StudentVO userid = new StudentVO();

		boolean first = true;
		boolean emp_login = true;

		first: while (first) {
			menuController.emp_stu_select();
			int select = Integer.parseInt(sc.nextLine());
			switch (select) {
			case 1: {
				UniView.menu_print("교직원 로그인 창 입니다.");
				EmployeeVO employeeVO = empController.emp_id_password(sc);
				emp_login: while (emp_login) {
					if (employeeVO.getEmp_id().equals("admin") && employeeVO.getEmp_password() == 1234) { // 맞았을 때
						empController.emp_login_complete();
						int add_delete_select = Integer.parseInt(sc.nextLine());
						switch (add_delete_select) {
						case 1: {
							SubjectVO subjectVO = menuController.insert_sub_menu(sc);
							int count = eservice.insertSubject(subjectVO);
							if (count > 0) {
								UniView.complete_message_print("강의 추가에 성공하셨습니다.");
							}

							break;
						}
						case 2: {
							UniView.menu_print("강의를 수정하는 창 입니다.");
							SubjectVO subject = empController.modify_sub(sc);
							int count = eservice.updateSubject(subject);
							if (count > 0) {
								UniView.complete_message_print("강의 수정에 성공하였습니다.");
							} else {
								UniView.fail_print("존재하지 않는 강의입니다. 다시 입력해주세요.");
							}
							break;
						}
						case 3: {
							UniView.menu_print("강의를 삭제하는 창 입니다.");
							System.out.print("삭제할 강의의 강의번호를 입력하세요. >> ");
							int count = eservice.deleteSubject(sc.nextLine());
							if (count > 0) {
								UniView.complete_message_print("강의 삭제에 성공하였습니다.");
							}
							break;
						}
						case 4: {
							UniView.menu_print("전체 강의를 조회하는 창 입니다.");
							List<SubjectVO> sublist = sservice.selectSubjectVO();
							UniView.print(sublist, "조회 가능한 강의 ");
							break;
						}
						case 5: {
							System.out.println("로그아웃이 완료되었습니다.");
							break first;
						}
						default: {
							UniView.fail_print("잘못된 번호를 입력하였습니다. 다시 입력해주세요.");
							break;
						}
						}
					} else { // 로그인이 틀렸을 때
						UniView.fail_print("아이디와 비밀번호가 틀렸습니다. 다시 입력해주세요.");
						break emp_login;
					}
				}
				break;
			}
			case 2: {
				studentController.welcomeStudent();
				int register_login_select = Integer.parseInt(sc.nextLine());
				switch (register_login_select) {
				case 1: {
					System.out.println("회원가입 창으로 가세요");
					StudentVO studentVO = register.StuRegister(sc);
					int count = sservice.insertStudent(studentVO);
					if (count > 0) { // 회원가입 완료하면
						// 해당 학생의 이름, 아이디 가져오기, 이메일을 기준으로
						String stu_name = studentVO.getStu_name();
						String stu_email = studentVO.getStu_email();
						int stu_id = sservice.getStuId(stu_email);
						System.out.println(stu_name + "님 회원가입 완료하셨습니다.");
						System.out.println(stu_name + "님의 학생 번호는 " + stu_id + "입니다.");
					}
					break;
				}
				case 2: {
					studentController.select_loginStudent();
					int select_login = Integer.parseInt(sc.nextLine());
					switch (select_login) {
					case 1: {// 로그인
						boolean stuLogin;
						while (true) {
							StudentVO student = studentController.loginStudent(sc);
							stuLogin = sservice.studentLogin(student);
							if (stuLogin) {
								String getName = sservice.studentLoginName(student.getStu_email());
								menuController.studentLoginComplete(getName);
								userid.setStu_email(student.getStu_email());
							} else {
								UniView.fail_print("로그인 정보가 일치하지 않습니다. 다시 입력해주세요.");
							}
							break;
						}
						enroll: while (true) {
							menuController.stu_login_menu();
							int login_select = Integer.parseInt(sc.nextLine());
							switch (login_select) {
							case 1: { // 수강 신청
								UniView.menu_print("수강신청 가능한 강의 목록입니다.");
								// 과목 리스트 출력하기
								List<SubjectVO> sublist = sservice.selectSubjectVO();
								UniView.print(sublist, "수강신청 가능한 ");
								// 여기까지 출력 완료
								System.out.print("수강신청할 과목의 번호를 입력하세요. >> ");
								// enrolllist에 추가하기 sub_id를 기준으로 추가하기
								String sub_id = sc.nextLine();
								// join으로 sub_id에 해당하는 값들을 enroll join으로 넣어오자
								// email로 id 가져오기
								int getId = sservice.selectStuid(userid.getStu_email());

								// insert
								int count = sservice.insertEnroll(getId, sub_id);
								// join
								if (count > 0) {
									UniView.complete_message_print("수강신청에 성공하셨습니다.");
									List<EnrollListVO> enrollList = sservice.enroll_join(getId, sub_id);
									UniView.print(enrollList, "수강 신청 ");
								}
								break;
							}
							case 2: { // 수강 신청 취소
								System.out.print("수강 신청 취소할 수강 신청 목록의 번호를 선택하세요 >> ");
								int count = sservice.deleteEnroll(Integer.parseInt(sc.nextLine()));
								if (count > 0) {
									UniView.complete_message_print("수강 신청 삭제에 성공하였습니다.");
								} else {
									UniView.fail_print("수강신청 삭제에 실패하였습니다. 다시 입력해주세요.");
								}
								break;
							}
							case 3: { // 수강 신청 내역 확인
								System.out.println("수강 신청 내역 확인");
								int stu_id = sservice.selectStuid(userid.getStu_email());
								List<EnrollListVO> enrollList = sservice.enroll_list(stu_id);
								UniView.print(enrollList, "수강 신청 ");
								
								break first;
							}
							case 4: { // 로그아웃
								UniView.complete_message_print("로그아웃 되었습니다.");
								UniView.complete_message_print("프로그램 종료");
								break first;
							}
							default:
								UniView.fail_print("잘못된 번호를 입력하셨습니다. 다시 입력하세요.");
								break;
							}

						}
					}
					case 2: { // 이메일 찾기
						UniView.menu_print("이메일을 찾는 창입니다.");
						System.out.print("찾고싶은 학생의 학생 번호를 입력하세요. >> ");
						int stu_id = Integer.parseInt(sc.nextLine());
						String email = sservice.findEmail(stu_id);
						if (email.isEmpty()) {
							System.out.println(email);
							break;
						} else {
							UniView.fail_print("학생의 이메일은 " + email + "입니다.");
							break;
						}

					}
					case 3: { // 비밀번호 찾기
						UniView.menu_print("비밀번호를 찾는 창입니다.");
						System.out.print("찾고싶은 학생의 이메일을 입력하세요. >> ");
						String stu_email = sc.nextLine();
						String password = sservice.findPassword(stu_email);

						if (!password.isEmpty()) {

							System.out.println("학생의 비밀번호는 " + password + "입니다.");
							UniView.alarm_message("비밀번호를 변경하시겠습니까?");
							System.out.print("yes(y) or no(n) >> ");
							String resetPassword = sc.nextLine();
							if (resetPassword.equals("yes") || resetPassword.equals("y")) {
								System.out.print("새로운 비밀번호를 입력하세요. >> ");
								String new_password = sc.nextLine();
								int count = sservice.updatePassword(new_password, stu_email);
								if (count > 0) {
									UniView.complete_message_print("비밀번호 수정에 성공하였습니다.");
								}
								break;
							} else if (resetPassword.equals("no") || resetPassword.equals("n")) {
								UniView.complete_message_print("비밀번호를 변경하지 않습니다.");
								break;
							}
						} else {
							UniView.fail_print("해당 이메일이 존재하지 않습니다. 다시 입력해주세요.");
							continue;
						}
						break;
					}

					default:
						break;
					}
					break;
				}
				default:
					UniView.fail_print("잘못된 번호를 입력하셨습니다. 다시 입력하세요.");
					break;

				}
				break;
			}
			case 3: {
				System.out.println("프로그램 종료");
				break first;
			}
			default: {
				UniView.fail_print("잘못된 번호를 입력하였습니다.");
			}
			}
		}
	}
}
