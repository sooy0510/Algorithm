package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_12514_문자열찾기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		String s1 = "KOI";
		int c1 = 0;
		String s2 = "IOI";
		int c2 = 0;
		String tmp=null;
		
		for(int i=0; i<input.length()-2; i++) {
			tmp = input.substring(i, i+3);
			if(tmp.equals(s1)) c1++;
			if(tmp.equals(s2)) c2++;
		}
		
		System.out.println(c1);
		System.out.println(c2);
	}

}
