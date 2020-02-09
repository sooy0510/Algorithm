package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_J_2858_쇠막대기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = 0; //길이
		int c = 0; //개수
		boolean flag = false; // "("는 false, ")"는 true
		String arr = br.readLine();
		
		for(int i=0; i<arr.length(); i++) {
			if(arr.charAt(i) == '(') {
				l++;
				c++;
				flag = false;
			}
			
			if(arr.charAt(i) == ')') {
				if(flag == false) {
					--l;
					--c;
					c += l;
					flag = true;
				}else {
					l--;
				}
			}
		}
		
		System.out.println(c);
	}

}