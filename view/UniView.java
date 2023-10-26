package com.seoyoung.view;

import java.util.List;

// 있어도 되고 없어도 된다.
public class UniView {
	public static <T> void print(List<T> list, String message) {
		System.out.println("---------------- 조회 ----------------");
		if(list.size() == 0) {
			System.out.println(message + "리스트가 존재하지 않습니다.");
		}
		for(T obj:list) {
			System.out.println(obj.toString());
		}
		System.out.println();
	}
	public static void fail_print(String message) {
		System.out.println();
		System.out.println("|경 고| => [" + message + "]");
		System.out.println();
	}
	public static void menu_print(String message) {
		System.out.println("=========================================");
		System.out.println();
		System.out.println("[" + message + "]");
		System.out.println();
		System.out.println("=========================================");
	}
	
	public static void show_menu_print(String message) {
		System.out.println("-----------------------------------------");
		System.out.println("[" + message + "]");
		System.out.println("-----------------------------------------");
	}
	
	public static void select_menu_print(String message) {
		System.out.println("-----------------------------------------");
		System.out.println(message);
		System.out.println("-----------------------------------------");
	}
	
	public static void complete_message_print(String message) {
		System.out.println();
		System.out.println(">> " + message +" <<");
		System.out.println();
	}
	public static void alarm_message(String message) {
		System.out.println();
		System.out.println("[" + message + "]");
		System.out.println();
	}
}
