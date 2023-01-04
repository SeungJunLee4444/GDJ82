package ex04_input;

import javax.swing.JOptionPane;

public class Ex01_JOptionPane {

	public static void main(String[] args) {

		// 1. JOpetionPane(자바옵션펜) * 공부할 필요는없음
		// => javax.swing.JOptionPane 클래스
		// (확장되서 x가 붙음)
		// => GUI 툴을 제공하는 클래스(java.awt)
		
		String name = JOptionPane.showInputDialog("이름을 입력하세요");
		String age = JOptionPane.showInputDialog("나이를 입력하세요");
		System.out.println(name);
		System.out.println(age);
	
		
	}

}
