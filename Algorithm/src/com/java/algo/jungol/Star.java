package com.java.algo.jungol;

public class Star {
	public static void main(String[] args) {
		int n = 5;
		
		//트리모양
		for(int i=0; i<n; i++) {
			for(int j=0; j<n+i; j++) {
				if((n-1)-j <= i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		//삼각형 아래
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if((n-1)-j <= i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
