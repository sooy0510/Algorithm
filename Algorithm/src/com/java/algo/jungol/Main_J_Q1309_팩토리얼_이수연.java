package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_Q1309_���丮��_�̼��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long sum = 1;
		
		for(int i=n; i>=1; i--) {
			if(i == 1) {
				System.out.print(i+"! = "+i+"\n");
			}else {
				System.out.print(i+"! = "+i+" * "+(i-1)+"!\n");
			}
			sum *= i;
		}
		
		System.out.println(sum);
		
	}

}
