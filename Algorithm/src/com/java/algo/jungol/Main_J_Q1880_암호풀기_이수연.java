package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_Q1880_��ȣǮ��_�̼��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = "abcdefghijklmnopqrstuvwxyz";
		String str2 = sc.nextLine();
		String input = sc.nextLine();
		char c;
		int idx;
		
		for(int i=0; i<input.length(); i++) {
			// �빮�� 65����, �ҹ��� 97����
			c = input.charAt(i);
			if(c>=65 && c< 97) {
				c += 32;
				idx = str1.indexOf(c);
				c = (char) (str2.charAt(idx)-32);
				System.out.print(c);
			}else if(c>=97) {
				idx = str1.indexOf(c);
				c = str2.charAt(idx);
				System.out.print(c);
			}else {
				System.out.print(c);
			}
		}
		
	}

}
