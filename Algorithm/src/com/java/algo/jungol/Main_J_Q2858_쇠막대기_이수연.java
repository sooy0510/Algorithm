package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_J_Q2858_�踷���_�̼��� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = 0; //����
		int c = 0; //����
		boolean flag = false; // "("�� false, ")"�� true
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
